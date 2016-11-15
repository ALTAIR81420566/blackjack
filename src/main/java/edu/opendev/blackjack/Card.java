package edu.opendev.blackjack;

import java.util.ArrayList;

/**
 * Created by gerit on 08.11.2016.
 */
public class Card {

    private Lear lear;
    private Value value;

    public Card(Lear lear, Value value) {
        this.lear = lear;
        this.value = value;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString() + " - "
                + lear.toString();
    }
}
