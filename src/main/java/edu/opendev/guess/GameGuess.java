package edu.opendev.guess;

import java.util.Random;

/**
 * Created by ralex on 18.08.16.
 */
public class GameGuess {

    private int max;
    private int value;
    private Respondent respondent;

    public GameGuess(int max, Respondent respondent) {
        this.max = max;
        this.respondent = respondent;
        Random rnd = new Random();
        this.value = rnd.nextInt(max) + 1;
    }

    /**
     * Начать играть
     */
    public void start() {
        System.out.printf("Я загадал число от 1 до %d, отгадайте его%n", max);
        int answer;
        do {
            answer = respondent.nextAnswer();
        } while (!checkAnswer(answer));
    }

    private boolean checkAnswer(int answer) {
        return checkAnswer(answer, false);
    }

    private boolean checkAnswer(int answer, boolean quiet) {
        boolean result = false;
        String message;
        if(answer > value) {
            message = "много";
        } else if(answer < value) {
            message = "мало";
        } else {
            message = "победа, Вы угадали";
            result = true;
        }
        if(!quiet) {
            System.out.println(message);
        }
        return result;
    }

}
