package edu.opendev.guess.player;

import edu.opendev.guess.GameGuess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ralex on 01.09.16.
 */
public class SmartRndBot extends Bot {

    private List<Integer> prevAnswersList = new ArrayList<>();

    protected Random rnd = new Random();

    public SmartRndBot(int max) {
        super(max);
    }

    @Override
    public String getName() {
        return "Бот-умный-рандом";
    }

    @Override
    public int nextAnswer(GameGuess.ResultCheck prevResultCheck) {
        int answer;
        do {
            answer = rnd.nextInt(max + 1);
        } while (prevAnswersList.contains(answer));

        return answer;
    }
}
