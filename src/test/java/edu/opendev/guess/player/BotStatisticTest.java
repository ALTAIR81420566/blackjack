package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * Created by ralex on 05.09.16.
 */
public class BotStatisticTest {

    private static final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
    private static final PrintStream memOut = new PrintStream(outBuffer);
    private static final PrintStream stdOut = System.out;

    private static final int max = 100;
    private final static GameGuess gg = new GameGuess(max);
    private long start;
    private long end;

    /**
     * после выполнения всех тестов вернуть стандартный вывод в консоль
     */
    @AfterClass
    public static void afterClass() {
        switchToStdOut();
    }

    @Test
    public void testRunnable() {

        //использует лямбду из Java 8, чтобы передать в метод runWithStdOut() указанный код на выполнение
        runWithStdOut(() -> System.out.println("************ test std output runnable by lambda *************"));

        //до восьмой жавы это выглядело бы так -
        runWithStdOut(new Runnable() {
            @Override
            public void run() {
                System.out.println("************ test std output runnable by anonymous class *************");
            }
        });
    }

    /**
     * Метод принимает код, который получит обычный консольный вывод для своей работы
     * @param runnable
     */
    private void runWithStdOut(Runnable runnable) {
        switchToStdOut();
        runnable.run();
        switchToMemOut();
    }

    /**
     * Переключить стандартный поток вывода на кэш в пямяти
     */
    private static void switchToMemOut() {
        System.setOut(memOut);
    }

    /**
     * Переключить стандартный поток вывода на указанный файл
     * @param fileName имя файла (вместе с путем)
     * @throws FileNotFoundException
     */
    private static void switchToFileOut(String fileName) throws FileNotFoundException {
        System.setOut(new PrintStream(fileName));
    }


    /**
     * Переключить стандартный поток вывода обратно на консоль
     */
    private static void switchToStdOut() {
        System.setOut(stdOut);
    }

    private static void printMemOut() {
        System.out.println(outBuffer.toString());
    }

}
