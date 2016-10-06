import Players.Human;
import Players.Player;
import deck.Deck;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Start {
    public static void main (String args[]){
        Deck deck =  new Deck();
        Player player = new Human();
        Engine engine = new Engine(player);
        engine.start();
    }


}
