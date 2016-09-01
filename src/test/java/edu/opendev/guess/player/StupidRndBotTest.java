package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by ralex on 01.09.16.
 */
public class StupidRndBotTest {

    private static final int max = 100;
    private long start;
    private long end;

    @Before
    public void before() {
        start = System.nanoTime();
    }

    @After
    public void after() {
        end = System.nanoTime();
        System.out.println("time " + (end - start)/1000000. + " msc");
        System.out.printf("%n");
    }

    @Test
    public void testRndBot() {
        Bot bot = new StupidRndBot(max);
        GameGuess gg = new GameGuess(max, bot);
        gg.start();
    }

    @Test
    public void testDiBot() {
        Bot bot = new DiBot(max);
        GameGuess gg = new GameGuess(max, bot);
        gg.start();
    }

    @Test
    public void testSmartRndBot() {
        Bot bot = new SmartRndBot(max);
        GameGuess gg = new GameGuess(max, bot);
        gg.start();
    }

}
