package edu.opendev;

import edu.opendev.guess.Bot;
import edu.opendev.guess.GameGuess;
import edu.opendev.guess.Player;

/**
 * типы данных
 * арифметические операции
 * операции отношения
 * битовые операции
 * классы Math и StrictMath
 * приведение типов
 */

public class Main {

    final static double pi = 3.14;//3.14D, 3.14F

    /**
     * Точка входа в приложение
     *
     * @param args параметры запуска
     */
    public static void main(String[] args) {

        GameGuess gg = new GameGuess(100, new Bot(100));
        gg.start();

        gg.setRespondent(new Player("Игрок-человек"));
        gg.start();

    }



}

