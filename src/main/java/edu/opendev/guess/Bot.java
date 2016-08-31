package edu.opendev.guess;

import java.util.Random;

/**
 * Created by ralex on 23.08.16.
 */
public class Bot implements Respondent {

    private int max;
    private Random rnd = new Random();

    public Bot(int max) {
        this.max = max;
    }

    @Override
    public String getName() {
        return "Бот, приспешник чистого рандома";
    }

    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer = rnd.nextInt(max+1);
        return answer;
    }
}
