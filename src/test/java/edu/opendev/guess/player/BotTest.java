package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;
import org.junit.*;

/**
 * Created by ralex on 01.09.16.
 */

public class BotTest {

    private static final int max = 100;
    private final static GameGuess gg = new GameGuess(max);
    private long start;
    private long end;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Тестирование ботов");
        System.out.println();
    }

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
        testBot(new StupidRndBot(max));
    }

    @Test
    public void testDiBot() {
        testBot(new DiBot(max));
    }

    @Test
    public void testSmartRndBot() {
        testBot(new SmartRndBot(max));
    }

    @Test
    public void testForceBot() {
        testBot(new ForceBot(max));
    }

    @Test
    public void testRndDiBot() {
        testBot(new RndDiBot(max));
    }

    private void testBot(Bot bot) {
        gg.setRespondent(bot);
        gg.start();
    }

}
