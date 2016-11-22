package edu.opendev.blackjack;

import edu.opendev.blackjack.Players.Human;
import edu.opendev.blackjack.Players.Player;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Start {
    public static void main (String args[]){
        Player player = new Human();
        Engine engine = new Engine(player);
        engine.start();
    }


}
