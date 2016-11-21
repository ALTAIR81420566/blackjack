package edu.opendev.blackjack.deck;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Card {
    private String suits[] = {"Треф","Пик","Червы","Буби"};
    private String namesAndValues[][] = {{"Двойка","2"},{"Тройка","3"},{"Четверка","4"},
            {"Пятерка","5"},{"Шестерка","6"},{"Семерка","7"},
            {"Восьмерка","8"},{"Девятка","9"},{"Десятка","10"},
            {"Валет","10"},{"Дама","10"},{"Король","10"},{"Туз","11"}};

    private String suit;
    private String name;
    private int value;

    Card(int suit,int name){
        this.suit = suits[suit];
        this.name = namesAndValues[name][0];
        this.value = Integer.parseInt(namesAndValues[name][1]);
    }

    public String getSuit() {
        return suit;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder =  new StringBuilder();
        stringBuilder.append(name).append(" ").append(suit).append(" ").append(value).append(" ").append("очков");
        return stringBuilder.toString();
    }
}
