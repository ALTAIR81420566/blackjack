package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

/**
 * Created by ralex on 05.09.16.
 */
public class ForceBot extends Bot {

    private int answer = 0;

    public ForceBot(int max) {
        super(max);
    }

    @Override
    public String getName() {
        return "Бот-брутфорс";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        return ++answer;
    }
}
