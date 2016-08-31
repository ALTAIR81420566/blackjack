package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

/**
 * Created by ralex on 31.08.16.
 */
public class RndBot extends Bot {

    public RndBot(int max) {
        super(max);
    }

    @Override
    public String getName() {
        return "Бот-чистый рандом";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer = rnd.nextInt(max+1);
        return answer;
    }


}
