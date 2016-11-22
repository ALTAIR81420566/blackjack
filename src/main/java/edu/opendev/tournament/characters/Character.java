package edu.opendev.tournament.characters;

import java.util.concurrent.Phaser;

/**
 * Created by Vlad on 21.11.2016.
 */
public class Character implements Runnable {
    protected String name;
    protected int damage;
    protected int health;
    protected int defence;
    protected Character opponent;
    protected Phaser drunkManager;

    public void setDrunkManager(Phaser drunkManager) {
        this.drunkManager = drunkManager;
    }

    public void setOpponent(Character opponent) {
        this.opponent = opponent;
    }
    protected Character(String name){
        this.name = name;
    }

    private void attack(int damage){
        if(damage > opponent.defence) {
            opponent.health -= damage - opponent.defence;
        }
    }

    public String getName() {
        return name;
    }
    public boolean isSleep(){
        return this.health <= 0;
    }

    @Override
    public void run() {
        drunkManager.register();
        while (this.health > 0 && opponent != null){

            int damage = (int) (defence + Math.random() * 10);

            attack(damage);

                if (opponent.isSleep()){
                    drunkManager.arriveAndAwaitAdvance();
                }
        }
        if(health <= 0) {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " - не выдержал и уснул");
        }

        drunkManager.arriveAndDeregister();
    }
}
