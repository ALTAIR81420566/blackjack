package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;
import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by ralex on 01.09.16.
 */
public class RndBotTest {

    @Test
    public void testRndBot() {
        RndBot bot = new RndBot(100);

        long start, end;

        start = System.nanoTime();

        GameGuess gg = new GameGuess(100, bot);
        gg.start();

        end = System.nanoTime();

        System.out.println("time " + (end - start)/1000000. + " msc");

    }

    @Test
    public void testDiBot() {
        DiBot bot = new DiBot(100);

        long start, end;

        start = System.nanoTime();

        GameGuess gg = new GameGuess(100, bot);
        gg.start();

        end = System.nanoTime();

        System.out.println("time " + (end - start)/1000000. + " msc");


    }
}
