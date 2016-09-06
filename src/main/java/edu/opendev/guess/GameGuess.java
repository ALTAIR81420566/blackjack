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
     * Класс для описания результатов игры
     */
    public class GameResult {
        private final String playerName;
        private final GameState state;

        /**
         * Приватный конструкор, наружу результаты могут попасть только от обрамляющего класса
         * @param playerName
         * @param state
         */
        private GameResult(String playerName, GameState state) {
            this.playerName = playerName;
            //здесь простое присваивание this.state = state не подходит, т.к. оно просто присвоит ссылку
            // на тот же объект, нужно сделать новый объект-копию переданного, и присвоить уже его
            this.state = new GameState(state);
        }

        public int getValue() {
            return state.value;
        }

        public int getCount() {
            return state.count;
        }

        public String getPlayerName() {
            return playerName;
        }
    }

    /**
     * Встроенный класс, описывает состояние игры
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
         * конструктор копирования
         * @param state
         */
        public GameState(GameState state) {
            //вызов другого конструктора, данном случае приватного
            this(state.max, state.value, state.count);
        }

        /**
         * Приватный конструтор, ислользуется конструктором копирования, можно было все сделать в констр.копирования,
         * но я добавил этот, чтобы продемонстрировать как вызывать из одного конструктора другой
         * @param max
         * @param value
         * @param count
         */
        private GameState(int max, int value, int count) {
            this.max = max;
            this.value = value;
            this.count = count;
        }

        public void reset() {
            this.count = 0;
            this.prevResultCheck = null;
        }
    }

    public static GameGuess getInstance() {
        return new GameGuess();
    }

    /**
     * Конструктор с полной инициализацией
     *
     * @param max
     * @param respondent
     */
    public GameGuess(int max, Respondent respondent) {
        Random rnd = new Random();
        int value = rnd.nextInt(max) + 1;

        this.state = new GameState(max, value);
        this.respondent = respondent;
    }

    /**
     * Конструктор по-умолчанию
     */
    public GameGuess() {
        this(100, new Player("Аноним"));
    }

    /**
     * Конструктор с частичной инициализацией
     * TODO переделать конструкторы на фабричный метод
     * @param max
     */
    public GameGuess(int max) {
        this(max, new Player("Аноним"));
    }


    public void setRespondent(Respondent respondent) {
        this.respondent = respondent;
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
        if (value < state.value) {
            r = ResultCheck.LEFT;
        } else if (value > state.value) {
            r = ResultCheck.RIGHT;
        }
        state.prevResultCheck = r;
        state.count++;
        return r;
    }

    /**
     * перед началом игры
     */
    private void before() {
        System.out.println("Приветствую, " + respondent.getName() + "!");
        System.out.printf("Я загадал число от 1 до %d, отгадайте его%n", state.max);
        state.reset();
        respondent.init();
    }

    /**
     * Начать играть
     */
    public GameResult start() {
        before();
        int answer;
        ResultCheck resultCheck;
        do {
            answer = respondent.nextAnswer(state.prevResultCheck);
            resultCheck = checkValue(answer);
            printResultCheck(resultCheck);
        } while (resultCheck != ResultCheck.MATCH);
        GameResult result = new GameResult(respondent.getName(), state);
        after(result);
        return result;
    }

    /**
     * после завершения игры
     * @param result
     */
    private void after(GameResult result) {
        System.out.println("Игра завершена, " + result.getPlayerName()) ;
        System.out.printf("Вы совершили %d попыток, загададное число: %d%n", result.getCount(), result.getValue());
    }

    private void printResultCheck(ResultCheck resultCheck) {
        if(this.respondent instanceof Player) {
            System.out.println(textMap.get(resultCheck));
        }
    }

}
