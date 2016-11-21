package edu.opendev.blackjack;

import edu.opendev.blackjack.Players.Diller;
import edu.opendev.blackjack.Players.Player;
import edu.opendev.blackjack.deck.Deck;

import java.util.Scanner;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Engine {

    private Player player;
    private Player diller;
    private Deck cardDeck = new Deck();
    private boolean finish = false;
    private boolean playerIsStep = false;
    private boolean win = false;
    private String answer = "";
    private int i = 0;

public void start(){
    startPlayer(player);
    if (!finish) {
        diller = new Diller();
        startPlayer(diller);
        checkResult();
    }
    if (win) {
        System.out.println("Как ты смог победить в этой игре человек?!Умри!!!");
        System.out.println("Введите бум, чтобы выстрелить или погибнете");
        Thread thread = new Thread() {
            public void run() {
                while (i < 5) {
                    i++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        Scanner scanner = new Scanner(System.in);
        answer = scanner.next();
        System.out.println(answer);
        if(!answer.equals("бум")|| i == 5){
            System.out.println("Вы не успели.Существо сидевшее напротив, обхватило вашу шею своими длинными пальцами.");
            System.out.println("Выбраться из железной хватки оказывается невозможно и вы понимаете что ваша жизнь закончится здесь.");
            System.out.println("Вы погибли.");
        }else {
            System.out.println("Выстрел достигает существа которое сидит напротив, раздается душераздирающий крик. Ваши глаза запоняет яркий свет.");
            System.out.println("Похоже вам очень повезло!После всего пережитого вами ужаса, вы очутились на тропическом острове с тремя горячими девушками, с кучей еды и алкоголя.");
            System.out.println("Полная победа!");
        }
    }

}
    public Engine(Player player){
        this.player = player;
    }

    private void printResult(Player one, Player two) {
        if (!finish) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Победил ")
                    .append(one.getName()).append(" с результатом ")
                    .append(one.getPoints()).append(" Против ")
                    .append(two.getName()).append(" с результатом ")
                    .append(two.getPoints())
                    .append("\nПоражение означает смерть. Вы не успеваете встать из за стола, как ваше горло перерезют ножом. Смерть наступает быстро,")
                    .append("\nвы успеваете только подумать, что не справедливо вот так умереть, но уже ничего не поделать, жизнь покинула ваше тело");
            System.out.println(stringBuilder);
        }
    }
    private void printResult(boolean blackjack, Player one){
        StringBuilder stringBuilder = new StringBuilder();
        String condition;
        String result;
        if (blackjack){
            condition = "Блек-Джек!";
            result = " выиграл";
        }else {
            condition = "Перебор!";
            result = " проиграл";

        }
        System.out.println(stringBuilder.append("Карты:")
                .append(one.getDeck())
                .append("\n").append(condition).append("\n")
                .append(one.getName()).append(result));

    }

    private void checkResult(){
        if(player.getPoints() > diller.getPoints()){
            printResult(player,diller);
            win = true;
        }else if (player.getPoints() < diller.getPoints()){
            printResult(diller,player);
        }else{
            System.out.println("Ничья! Но ничьи быть не может. Из тьмы вылезаю щупальца, хватают вас за руки и за ноги и разрывают на части");
        }
    }

    private void startPlayer(Player player){
        int points;
        player.getCard(cardDeck);
        do {
            player.getCard(cardDeck);
            points = player.getPoints();
            if (points == 21){
                finish = true;
                printResult(true,player);
                if (player == this.player){
                   win = true;
                }
                break;
            }else  if (points > 21){
                finish = true;
                printResult(false,player);
                if (player == this.diller){
                    win = true;
                }
                break;
            }

        }while(player.isSay() == true);
    }
}