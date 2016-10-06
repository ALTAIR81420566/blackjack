package deck;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();

    public ArrayList<Card> getDeck(){
        return deck;
    }
    public Deck(){
        ArrayList<Card> deck = createDeck();

        this.deck.addAll(mix(deck));

    }

    private ArrayList<Card> createDeck(){
        ArrayList<Card> deck = new ArrayList<Card>();
        for (int i = 0;i < 4;i++) {
            for (int j = 0;j < 13;j++){
                deck.add(new Card(i,j));
            }
        }
        return deck;
    }
private ArrayList<Card> mix( ArrayList<Card> cards){
    ArrayList<Card> mixed = new ArrayList<Card>();
    Random randID = new Random();
    while (!cards.isEmpty()){
        int id = randID.nextInt(cards.size());
        mixed.add(cards.get(id));
        cards.remove(id);
    }
    return mixed;
    }
    public Card getCard(){
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }
}
