package edu.opendev.blackjack;

/**
 * Created by gerit on 10.11.2016.
 */
public class Player {

    private String name;
    private int countCards;
    private Card[] hund = new Card[10];

    public Player(String name) {
        this.name = name;
    }

    public void resetHund() {
        for (int i = 0; i < countCards; i++) {
            hund[i] = null;
        }
        countCards = 0;
    }

    public int getCountPoints() {
        int countPoints = 0;
        int countAces = 0;

        for (Value value : Value.values()) {
            for (int i = 0; i < countCards; i++) {
                if (hund[i].getValue() == value) {
                    if (hund[i].getValue() == Value.ACE) {
                        countAces += 1;
                    }
                    countPoints += value.getValueCard();
                }
            }
        }

        for (int i = 0; i < countAces; i++) {
            if (countPoints > 10) {
                countPoints += 1;
            } else {
                countPoints += Value.ACE.getValueCard();
            }
        }
        return countPoints;
    }

    public void addCard(Card card){
        hund[countCards] = card;
        countCards++;
    }

    public boolean isAddCard(){
        return (getCountPoints() <= 21);
    }

    public void outputToScreenHund(boolean showFirstCard){
        System.out.printf("\n%s cards: \n" , name);
        for (int i = 0; i < countCards; i++){
            if(i == 0 && !showFirstCard){
                System.out.println("[Скрытая карта]");
            }
            else{
                System.out.printf("%s\n", hund[i].toString());
            }
        }
    }
}
