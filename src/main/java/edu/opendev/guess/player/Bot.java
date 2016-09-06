package edu.opendev.guess.player;

import java.util.Random;

/**
 * Created by ralex on 23.08.16.
 */
abstract public class Bot implements Respondent {

    protected int max;

    public Bot(int max) {
        this.max = max;
    }

}
