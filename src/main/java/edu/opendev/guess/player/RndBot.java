package edu.opendev.guess.player;

import edu.opendev.guess.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ralex on 31.08.16.
 */
public class RndBot extends Bot {

    private List<Integer> list = new ArrayList<>();

    public RndBot(int max) {
        super(max);
    }

    @Override
    public String getName() {
        return "Бот-чистый рандом";
    }

    @Override
    public int nextAnswer(Game.ResultCheck prevResultCheck) {

        int num;
        do {
            num = rnd.nextInt(max+1);
        } while (list.contains(num));

        int answer = num;
        list.add(answer);

        return answer;
    }

}
