package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

import java.util.Random;

/**
 * Created by ralex on 31.08.16.
 */
public class StupidRndBot extends Bot {

    private Random rnd = new Random();

    public StupidRndBot(int max) {
        super(max);
    }

    @Override
    public String getName() {
        return "Бот-тупой рандом";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer = rnd.nextInt(max+1);
        return answer;
    }


}
