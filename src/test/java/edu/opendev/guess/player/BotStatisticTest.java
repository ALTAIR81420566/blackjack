package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;
import org.junit.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ralex on 05.09.16.
 */
public class BotStatisticTest {

    private static final ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
    private static final PrintStream memOut = new PrintStream(outBuffer);
    private static final PrintStream stdOut = System.out;
    private final int max = 100;

    private class StatsHelper {
        private final int countOfRuns;
        private List<Bot> bots = new ArrayList<>();
        private Map<String, List<RunResult>> runResultMap = new HashMap<>();
        private Map<String, AverageResult> averageResultMap = new HashMap<>();

        private StatsHelper(int countOfRuns) {
            this.countOfRuns = countOfRuns;
        }

        private class RunResult {
            private double timeMs;
            private int countTry;
            private int value;
        }

        private class AverageResult {
            private double averageTime;
            private double averageCountTry;
        }

        public StatsHelper addBot(Bot bot) {
            bots.add(bot);
            return this;
        }

        public StatsHelper makeStats() {
            for (int i = 0; i < countOfRuns; i++) {
                GameGuess gg = new GameGuess(max);
                run(gg);
            }
            calculateAverage();
            return this;
        }

        private void calculateAverage() {
            for (Map.Entry<String, List<RunResult>> entry : runResultMap.entrySet()) {
                String name = entry.getKey();
                List<RunResult> runResults = entry.getValue();

                double totalTime = 0;
                int totalCount = 0;
                for (RunResult runResult : runResults) {
                    totalTime += runResult.timeMs;
                    totalCount += runResult.countTry;
                }
                AverageResult averageResult = new AverageResult();
                averageResult.averageTime = totalTime / countOfRuns;
                averageResult.averageCountTry = (double) totalCount / countOfRuns;
                averageResultMap.put(name, averageResult);
            }
        }

        private void run(GameGuess gg) {
            long start, end;
            GameGuess.GameResult gameResult;
            for (Bot bot : bots) {
                gg.setRespondent(bot);
                start = System.nanoTime();
                gameResult = gg.start();
                end = System.nanoTime();

                RunResult runResult = new RunResult();
                runResult.timeMs = (end - start) / 1000_000.;
                runResult.countTry = gameResult.getCount();
                runResult.value = gameResult.getValue();

                List<RunResult> runResultList;
                runResultList = runResultMap.containsKey(bot.getName())
                        ? runResultMap.get(bot.getName()) : new ArrayList<>();
                runResultList.add(runResult);
                runResultMap.put(bot.getName(), runResultList);
            }
        }

        public StatsHelper printStats() {

            runWithStdOut(() -> {
                System.out.println("Количество испытаний: " + countOfRuns);
                for (Map.Entry<String, AverageResult> entry : averageResultMap.entrySet()) {
                    String name = entry.getKey();
                    AverageResult result = entry.getValue();
                    System.out.println("Игрок: " + name);
                    System.out.println("\tСреднее число попыток: " + result.averageCountTry);
                    System.out.println("\tСреднее время выполнения(мс): " + result.averageTime);
                }
            });

            return this;
        }

        public void printResults() {

            runWithStdOut(() -> {
                for (Map.Entry<String, List<RunResult>> entry : runResultMap.entrySet()) {
                    String name = entry.getKey();
                    List<RunResult> runResults = entry.getValue();
                    System.out.println("Игрок: " + name);
                    System.out.println("Количество испытаний: " + runResults.size());
                    for (RunResult runResult : runResults) {
                        System.out.print("\tЗагаданное число: " + runResult.value);
                        System.out.print(", число попыток: " + runResult.countTry);
                        System.out.println(", время выполнения(мс): " + runResult.timeMs);
                    }

                }
            });

        }

    }

    @BeforeClass
    public static void beforeClass() {
        switchToMemOut();
    }

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

    @Test(timeout = 5000)
    public void makeStats() {
        StatsHelper statsHelper = new StatsHelper(100);
        statsHelper
                .addBot(new RndDiBot(max))
                .addBot(new SmartRndBot(max))
                .addBot(new StupidRndBot(max))
                .addBot(new DiBot(max))
                .addBot(new ForceBot(max))
                .makeStats()
                .printStats();
                //.printResults();
    }

    /**
     * Метод принимает код, который получит обычный консольный вывод для своей работы
     *
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
     *
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
