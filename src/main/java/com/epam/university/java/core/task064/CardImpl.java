package com.epam.university.java.core.task064;

public class CardImpl implements Card {
    private int cardRank;
    private Suit cardSuit;

    @Override
    public int getCardRank() {
        return cardRank;
    }

    @Override
    public void setCardRank(int cardRank) {
        this.cardRank = cardRank;
    }

    public Suit getSuit() {
        return cardSuit;
    }

    @Override
    public void setCardSuit(Suit cardSuit) {
        this.cardSuit = cardSuit;
    }
}
