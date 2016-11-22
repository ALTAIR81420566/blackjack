package edu.opendev.tournament;

import edu.opendev.tournament.characters.Character;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * Created by Vlad on 21.11.2016.
 */
public class DrunkTournament {

    public Phaser drunkManager =  new Phaser();
    private ArrayList<Character> characters = new ArrayList<>();
    public DrunkTournament(ArrayList<Character> characters){
        this.characters = characters;
        createCouple();

    }

    private void deleteCouple(){
        Iterator<Character> it = characters.iterator();
        while (it.hasNext()){
            Character character = it.next();
            if(character.isSleep()){
                it.remove();
            }
        }
    }

    private void createCouple(){
        StringBuilder sb = new StringBuilder();
        System.out.println("");
        for(int i = 0; i < characters.size(); i+=2){
            if(i != characters.size()-1) {
                characters.get(i).setOpponent(characters.get(i + 1));
                characters.get(i + 1).setOpponent(characters.get(i));
                sb.append(characters.get(i).getName()).append(" усаживается за стол, а напротив садиться ")
                        .append(characters.get(i + 1).getName());
                System.out.println(sb);
                sb.delete(0, sb.length());
            }
        }
        System.out.println("");
    }

    public void start(){
        ExecutorService exec = Executors.newCachedThreadPool();

        for(Character character : characters){
            character.setDrunkManager(drunkManager);
            exec.execute(character);
        }
        exec.execute(new TournamentOrganizer());

        exec.shutdown();

    }

    private class TournamentOrganizer implements Runnable{
        @Override
        public void run() {
            drunkManager.register();
            while (characters.size() > 1) {
                drunkManager.arriveAndAwaitAdvance();
                try {
                    deleteCouple();
                    if(characters.size() > 1) {
                        for (Character character : characters) {
                            Thread.sleep(600);
                            System.out.println(character.getName() + " - смог перепить оппонента и хочет еще");
                        }
                    }else {
                        characters.get(0).setOpponent(null);
                        Thread.sleep(2000);
                        System.out.println(characters.get(0).getName() +
                                " - перепил всех! Став при этом коронованым принцем всего двора. Теперь он заслуженно проведет ночь с Принцессой. Желаем ему удачи!");
                    }
                    createCouple();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            drunkManager.arriveAndDeregister();
        }
    }
}
