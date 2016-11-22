package edu.opendev.tournament;

import edu.opendev.tournament.characters.Character;
import edu.opendev.tournament.characters.OldGuy;
import edu.opendev.tournament.characters.YoungGuy;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vlad on 21.11.2016.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Character> characters = new ArrayList<>(
                Arrays.asList(new OldGuy("Василий Петрович"),
                        new OldGuy("какой то мужик"),
                        new OldGuy("Валера Режиссер"),
                        new OldGuy("Рыбий глаз"),
                        new YoungGuy("Панин"),
                        new YoungGuy("Чубака"),
                        new YoungGuy("Скользкий Джо"),
                        new YoungGuy("Коля Пирожок")));
        DrunkTournament drunkTournament = new DrunkTournament(characters);
        drunkTournament.start();

    }
}
