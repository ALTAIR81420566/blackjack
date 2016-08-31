package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

/**
 * Created by ralex on 23.08.16.
 */
public interface Respondent {

    String getName();
    int nextAnswer(GameGuess.ResultCheck prevResultCheck);

}
