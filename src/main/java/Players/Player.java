package Players;

import deck.Card;
import deck.Deck;

import java.util.ArrayList;

/**
 * Created by Vlad on 03.10.2016.
 */
public interface Player {
    String getName();
    void getCard(Deck deck);
    boolean isSay();
    public int getPoints();
    ArrayList<Card> getDeck();
}
