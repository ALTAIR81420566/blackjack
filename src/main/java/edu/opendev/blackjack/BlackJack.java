package edu.opendev.blackjack;

import javafx.scene.layout.Pane;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ralex on 13.09.16.
 */
public class BlackJack {

    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.createDeck();
        deck.mixCards();

        Player player = new Player("Jimm");
        Player dealer = new Player("Dealer");

        player.addCard(deck.getNextCard());
        dealer.addCard(deck.getNextCard());
        player.addCard(deck.getNextCard());
        dealer.addCard(deck.getNextCard());

        System.out.println("Полученные карты: ");
        player.outputToScreenHund(true);
        dealer.outputToScreenHund(false);
        System.out.println("\n");

        boolean playerFinished = false;
        boolean dealerFinshed = false;
        int userResponse;
        Scanner scanner = new Scanner(System.in);

        while (!playerFinished || !dealerFinshed) {
            if (!playerFinished) {

                    System.out.println("Взять ещё карту(1) или остановиться(2)?");
                    userResponse = scanner.nextInt();

                    if (userResponse == 1) {
                        playerFinished = !player.isAddCard();
                        dealer.outputToScreenHund(true);
                    } else if(userResponse == 2){
                        playerFinished = true;
                    } else {
                        System.out.println("Данная команда отсутствует.");
                    }
            }

            if (!dealerFinshed) {

                if (dealer.getCountPoints() < 17) {
                    System.out.println("Диллер берет ещё");
                    dealerFinshed = !dealer.isAddCard();
                    dealer.outputToScreenHund(false);
                } else {
                    System.out.println("Диллер остановился.");
                    dealerFinshed = true;
                }
            }
            System.out.println();
        }

        scanner.close();
        player.outputToScreenHund(true);
        dealer.outputToScreenHund(true);

        if (player.getCountPoints() > dealer.getCountPoints() &&
                player.getCountPoints() <= 21 ||
                dealer.getCountPoints() > 21) {
            System.out.println("\nВы победили!Сумма ваших очков: " + player.getCountPoints());
            System.out.println("Диллер проиграл!Сумма очков диллера:" + dealer.getCountPoints());
        } else {
            System.out.println("\nВы проиграли!Сумма ваших очков: " + player.getCountPoints());
            System.out.println("Диллер выиграл!Сумма очков диллера:" + dealer.getCountPoints());
        }
    }
}
