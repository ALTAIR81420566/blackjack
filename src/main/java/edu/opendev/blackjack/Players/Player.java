package edu.opendev.blackjack.Players;

import edu.opendev.blackjack.deck.Card;
import edu.opendev.blackjack.deck.Deck;

import java.util.ArrayList;

/**
 * Created by Vlad on 03.10.2016.
 */
public interface Player {
    String getName();
    void getCard(Deck deck);
    boolean isSay();
    int getPoints();
    ArrayList<Card> getDeck();
}
