package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

/**
 * Created by ralex on 31.08.16.
 */
public class DiBot extends Bot {

    private int prevAnswer;
    private int left;
    private int right;

    public DiBot(int max) {
        super(max);
        init();
    }

    @Override
    public String getName() {
        return "Бот-дихотомист";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer;

        if (prevResultCheck == GameGuess.ResultCheck.LEFT) {
            left = prevAnswer;
        } else if (prevResultCheck == GameGuess.ResultCheck.RIGHT) {
            right = prevAnswer;
        }

        answer = left + (right - left) / 2;

        prevAnswer = answer;
        return answer;
    }

    @Override
    public void init() {
        left = 0;
        right = max;
        prevAnswer = 0;
    }
}
