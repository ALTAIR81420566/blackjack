package edu.opendev.blackjack;

import edu.opendev.blackjack.Players.Diller;
import edu.opendev.blackjack.Players.Human;
import edu.opendev.blackjack.deck.Card;
import edu.opendev.blackjack.deck.Deck;
import org.junit.Before;
import org.junit.Test;

import java.util.EmptyStackException;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by ralex on 13.09.16.
 */
public class BlackJackTest {
    Deck cardDeck = new Deck();
    private Diller croupierBot;
    private List<Card> playerDeck;
    private Human human;


    @Test
    public void MakeCardDeckTest() {
        assertNotNull(cardDeck);
    }

    @Test
    public void getCardTest() {
        Card gameCard = cardDeck.getCard();
        assertNotNull(gameCard);
    }

    @Test(expected = EmptyStackException.class)
    public void getCardFromEmptyDeck() {
        Deck cardDeck = new Deck();
        for (int i = 0; i < 53; i++) {
            cardDeck.getCard();
        }
    }
    @Test(expected = IllegalArgumentException.class)
    public void negativeSuitIdGameCard(){
        Card gameCard = new Card(-5,5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeNameIdGameCard(){
        Card gameCard = new Card(2,-9);
    }

    @Test(expected =  IllegalArgumentException.class)
    public void overlyHighestSuitIdGameCard(){
        Card gameCard = new Card(5,3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void overlyHighestNameIdGameCard(){
        Card gameCard = new Card(2,13);
    }

    @Test
    public void  GameCardTest(){
        Card gameCard =  new Card(1,3);
        assertEquals("Трефы", gameCard.getSuit());
        assertEquals("Пятёрка", gameCard.getName());
    }

    @Before
    public void init(){
        croupierBot = new Diller();
        playerDeck = croupierBot.getDeck();
    }

    @Test
    public void addCardTest2(){
        Card gameCard = new Card(1,5);
        croupierBot.addCard(gameCard);
        assertEquals(1,playerDeck.size());
        assertTrue(croupierBot.getPoints() == gameCard.getPoint());
    }

    @Test
    public void cleanTest(){
        croupierBot.clean();
        assertEquals(0,playerDeck.size());
        assertTrue(croupierBot.getPoints() == 0);
    }

    @Test
    public void isEnoughTest(){
        Deck deck =  new Deck();
        while (croupierBot.getPoints() < 17){
            croupierBot.addCard(deck.getCard());
        }
        assertTrue(croupierBot.isSay());
    }

    @Test
    public void addCardTest(){
        Card gameCard = new Card(1,5);
        human.addCard(gameCard);
        assertEquals(1,playerDeck.size());
        assertTrue(human.getPoints() == gameCard.getPoint());
    }

    @Test
    public void cleanTest2(){
        human.clean();
        assertEquals(0,playerDeck.size());
        assertTrue(human.getPoints() == 0);
    }

}