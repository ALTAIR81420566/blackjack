package edu.opendev.guess.player;

import edu.opendev.guess.Game;

/**
 * Created by ralex on 31.08.16.
 */
public class DiBot extends Bot {

    private int prevAnswer;
    private int left;
    private int right;

    public DiBot(int max) {
        super(max);
        left = 0;
        right = max;
    }

    @Override
    public String getName() {
        return "Бот-дихотомист";
    }

    @Override
    public int nextAnswer(Game.ResultCheck prevResultCheck) {
        int answer;

        if (prevResultCheck == Game.ResultCheck.LEFT) {
            left = prevAnswer;
        } else if (prevResultCheck == Game.ResultCheck.RIGHT) {
            right = prevAnswer;
        }

        answer = left + (right - left) / 2;

        prevAnswer = answer;
        return answer;
    }
}
