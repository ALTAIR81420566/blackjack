package edu.opendev.guess.player;

import java.util.Random;

/**
 * Created by ralex on 23.08.16.
 */
abstract public class Bot implements Respondent {

    protected int max;
    protected Random rnd = new Random();

    public Bot(int max) {
        this.max = max;
    }

}
