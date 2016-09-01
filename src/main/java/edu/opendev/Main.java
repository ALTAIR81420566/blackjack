package edu.opendev;

import edu.opendev.guess.GameGuess;
import edu.opendev.guess.player.DiBot;
import edu.opendev.guess.player.Player;
import edu.opendev.guess.player.StupidRndBot;

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

        int max = 100;



        GameGuess gg = new GameGuess(max, new StupidRndBot(max));
        gg.start();

        gg.setRespondent(new DiBot(max));
        gg.start();


        gg.setRespondent(new Player("Игрок-человек"));
        gg.start();

    }



}

