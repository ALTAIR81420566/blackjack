package edu.opendev.guess.player;

import org.junit.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

/**
 * Created by ralex on 01.09.16.
 */
public class RndBotTest {

    @Test
    public void test() {
        RndBot bot = new RndBot(100);

        long start, end;

        start = System.nanoTime();

        for (int i = 0, a; i < 100; i++) {
            a = bot.nextAnswer(null);
        }

        end = System.nanoTime();
        System.out.println("time " + (end - start)/1000000. + " msc");





    }
}
