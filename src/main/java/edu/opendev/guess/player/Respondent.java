package edu.opendev.guess.player;

import edu.opendev.guess.Game;

/**
 * Created by ralex on 23.08.16.
 */
public interface Respondent {

    String getName();
    int nextAnswer(Game.ResultCheck prevResultCheck);

}
