package edu.opendev.guess;

/**
 * Created by ralex on 23.08.16.
 */
public interface Respondent {

    String getName();
    int nextAnswer(GameGuess.ResultCheck prevResultCheck);

}
