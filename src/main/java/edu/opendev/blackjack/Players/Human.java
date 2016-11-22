package edu.opendev.blackjack.Players;

import edu.opendev.blackjack.deck.Card;
import edu.opendev.blackjack.deck.Deck;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Vlad on 03.10.2016.
 */
public class Human implements Player {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private int points;
    private String name;



    public String getName() {
        return name;
    }

    public void getCard(Deck deck) {
        Card card = deck.getCard();
        cards.add(card);
        points += card.getValue();
    }

    public boolean isSay() {
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.append("Ваши карты:\n")
                .append(getDeck()).append("\nВсего очков: ")
                .append(points));

        Scanner scanner = new Scanner(System.in);
        String strToConsol;
        boolean answer;
        while (true){
            strToConsol = scanner.next();
            if (strToConsol.trim().equals("оставить")){
                answer = false;
                break;
            }else  if(strToConsol.trim().equals("взять")){
                answer = true;
                break;
            }else {
                System.err.println("Введено неверное значение! Повторите попытку.");
            }
        }
        return answer;
    }
    public int getPoints() {
        return points;
    }

    public ArrayList<Card> getDeck() {
        return cards;
    }

    public Human() {
        System.out.println("Как вас зовут?:\n");
        Scanner scanner = new Scanner(System.in);
        name = scanner.next();
        StringBuilder builder = new StringBuilder();
        System.out.println(builder.append("Добро пожаловать: ")
                .append(name)
                .append("\nВы попали в место, откуда еще никто не выбирался, вам будет предложенно сыграть в игру Блек Джек c существом сидящим напротив, где ставкой будет ваша жизнь.")
                .append("\nТеперь никаких вопросов. Мы начинаем наше шоу!")
                .append("\nЕсли хотите взять дополнительную карту, введите - \"взять\"")
                .append("\nЕсли хотите оставить все как есть, введите - \"оставить\""));
    }

    public void clean() {
        cards.removeAll(cards);
        points = 0;
    }

    public void addCard(Card gameCard) {
        cards.add(gameCard);
        points += gameCard.getPoint();
    }
}