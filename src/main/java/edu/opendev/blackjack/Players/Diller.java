package edu.opendev.blackjack.Players;

import edu.opendev.blackjack.deck.Card;
import edu.opendev.blackjack.deck.Deck;

import java.util.ArrayList;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Diller implements Player {
    private ArrayList<Card> cards = new ArrayList<>();
    private int points;
    private boolean isSay;
    private  String name;
    public String getName() {
        return name;
    }

    public void getCard(Deck deck) {
        Card card = deck.getCard();
        cards.add(card);
        points += card.getValue();
    }

    public boolean isSay() {
        if (points < 17){
            isSay = true;
        }else {
            isSay = false;
        }
        return isSay;

    }
    public ArrayList<Card> getDeck(){
        return cards;
    }
    public Diller(){
        this.name = "Диллер";
    }

    public int getPoints() {
        return points;
    }

    public void addCard(Card gameCard) {
        cards.add(gameCard);
        points += gameCard.getPoint();
    }

    public void clean() {
        cards.removeAll(cards);
        points = 0;
    }

}
