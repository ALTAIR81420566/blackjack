package edu.opendev.guess;

import javax.swing.*;
import java.util.Scanner;

/**
 * Created by ralex on 23.08.16.
 */
public class Player implements Respondent {

    @Override
    public int nextAnswer() {
        int answer;
        System.out.println("ваш ответ:");
        Scanner in = new Scanner(System.in);
        answer = in.nextInt();
        //answer = Integer.parseInt(JOptionPane.showInputDialog(null, "Ваш ответ: "));
        return answer;
    }
}
