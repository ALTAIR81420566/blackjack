package edu.opendev;

import edu.opendev.guess.GameGuess;
import edu.opendev.guess.Guesser;
import edu.opendev.guess.Player;

import java.io.FileNotFoundException;

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
    public static void main(String[] args) throws FileNotFoundException {
        GameGuess gg = new GameGuess(100, new Player());
        gg.start();

        GameGuess gg2 = new GameGuess(100, new Guesser(100));
        gg2.start();


    }



}

