package edu.opendev.guess;

import edu.opendev.guess.player.Player;
import edu.opendev.guess.player.Respondent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by ralex on 18.08.16.
 */
public class GameGuess {

    /**
     * Угадывающий (умеет давать предположительный ответ)
     */
    private Respondent respondent;

    /**
     * Состояние игры
     */
    private GameState state;

    /**
     * Коллекция типа Map, для сопоставления результатов проверки и их текстового описания
     */
    private static final Map<ResultCheck, String> textMap = new HashMap<ResultCheck, String>() {
        {
            put(ResultCheck.LEFT, "мало");
            put(ResultCheck.RIGHT, "много");
            put(ResultCheck.MATCH, "Победа!");
        }
    };

    /**
     * Тип перечисление для результатов проверки
     */
    public enum ResultCheck {
        LEFT, MATCH, RIGHT;

    }

    /**
     * Встроенный класс, описывает состояние игры и умеет проверять значение
     */
    private class GameState {
        /**
         * верхнаяя граница (включительно) для загаданного числа
         */
        private int max;

        /**
         * загаданное число от 1 до {@link GameState#max}
         */
        private int value;

        /**
         * чисто совершенных попыток отгадывания
         */
        private int count = 0;

        /**
         * Результаты проверки предыдущего ответа
         */
        private ResultCheck prevResultCheck = null;

        /**
         * Обязательный конструктор
         *
         * @param max
         * @param value
         */
        public GameState(int max, int value) {
            this.max = max;
            this.value = value;
        }

        /**
         * Проверяет число, если оно меньше, больше или равно загаданному, вернет соответственно ResultCheck.LEFT,
         * ResultCheck.RIGHT или ResultCheck.MATCH
         * Обновляет поле {@link GameState#prevResultCheck }
         *
         * @param value
         * @return
         */
        public ResultCheck checkValue(int value) {
            ResultCheck r = ResultCheck.MATCH;
            if (value < this.value) {
                r = ResultCheck.LEFT;
            } else if (value > this.value) {
                r = ResultCheck.RIGHT;
            }
            this.prevResultCheck = r;
            this.count++;
            return r;
        }

        public void reset() {
            this.count = 0;
            this.prevResultCheck = null;
        }
    }

    /**
     * Конструктор с полной инициализацией
     *
     * @param max
     * @param respondent
     */
    public GameGuess(int max, Respondent respondent) {
        init(max, respondent);
    }

    /**
     * Конструктор по-умолчанию
     */
    public GameGuess() {
        init(100, new Player("Аноним"));
    }

    /**
     * Конструктор с частичной инициализацией
     * TODO переделать конструкторы на фабричный метод
     * @param max
     */
    public GameGuess(int max) {
        init(max, new Player("Аноним"));
    }

    private void init(int max, Respondent respondent) {
        Random rnd = new Random();
        int value = rnd.nextInt(max) + 1;

        this.state = new GameState(max, value);
        this.respondent = respondent;
    }

    public void setRespondent(Respondent respondent) {
        this.respondent = respondent;
    }

    /**
     * перед началом игры
     */
    private void before() {
        System.out.println("Приветствую, " + respondent.getName() + "!");
        System.out.printf("Я загадал число от 1 до %d, отгадайте его%n", state.max);
        state.reset();
    }

    /**
     * Начать играть
     */
    public void start() {
        before();
        int answer;
        ResultCheck resultCheck;
        do {
            answer = respondent.nextAnswer(state.prevResultCheck);
            resultCheck = state.checkValue(answer);
            printResultCheck(resultCheck);
        } while (resultCheck != ResultCheck.MATCH);
        after();
    }

    /**
     * после завершения игры
     */
    private void after() {
        System.out.println("Игра завершена, " + respondent.getName()) ;
        System.out.printf("Вы совершили %d попыток, загададное число: %d%n", state.count, state.value);
    }

    private void printResultCheck(ResultCheck resultCheck) {
        if(this.respondent instanceof Player) {
            System.out.println(textMap.get(resultCheck));
        }
    }


}
