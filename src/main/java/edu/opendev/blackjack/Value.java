package edu.opendev.blackjack;

/**
 * Created by gerit on 08.11.2016.
 */
public enum  Value {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private int valueCard;

    public int getValueCard() {
        return valueCard;
    }

    Value(int valueCard) {
        this.valueCard = valueCard;
    }
}
