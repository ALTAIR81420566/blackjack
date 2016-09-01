package edu.opendev.guess.player;

import edu.opendev.guess.Game;

import java.util.Scanner;

/**
 * Created by ralex on 23.08.16.
 */
public class Player implements Respondent {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int nextAnswer(Game.ResultCheck prevResultCheck) {

        System.out.println("ваш ответ:");

        Integer answer = null;
        String input;
        Scanner in = new Scanner(System.in);

        while (answer == null) {
            input = in.nextLine();
            try {
                answer = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ошибка ввода");
            }
        }

        return answer;
    }

}
