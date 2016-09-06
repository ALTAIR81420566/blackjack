package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

/**
 * Created by ralex on 05.09.16.
 */
public class RndDiBot extends RndBot {

    private int prevAnswer;
    private int left;
    private int right;

    public RndDiBot(int max) {
        super(max);
        left = 0;
        right = max;
    }

    @Override
    public String getName() {
        return "Бот-гибрид";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer;

        if (prevResultCheck == GameGuess.ResultCheck.LEFT) {
            left = prevAnswer;
        } else if (prevResultCheck == GameGuess.ResultCheck.RIGHT) {
            right = prevAnswer;
        }

        answer = left + rnd.nextInt(right - left) + 1;

        prevAnswer = answer;
        return answer;
    }
}
