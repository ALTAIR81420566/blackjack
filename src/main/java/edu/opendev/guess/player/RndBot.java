package edu.opendev.guess.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by ralex on 05.09.16.
 */
public abstract class RndBot extends Bot {

    protected Random rnd = new Random();

    public RndBot(int max) {
        super(max);
    }

}
