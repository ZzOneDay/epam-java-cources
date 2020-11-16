package com.epam.university.java.core.task064;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Task064Impl implements Task064 {
    @Override
    public Player determineWinner(String firstHand, String secondHand, String board) {
        if (firstHand == null || secondHand == null || board == null) {
            throw new IllegalArgumentException();
        }

        if (!firstHand.contains(",")
                || !secondHand.contains(",")
                || !board.contains(",")) {
            throw new IllegalArgumentException();
        }


        Player firstPlayer = new PlayerImpl();
        firstPlayer.setId(1);

        Player secondPlayer = new PlayerImpl();
        secondPlayer.setId(2);

        String[] first = firstHand.split(",");
        String[] second = secondHand.split(",");
        String[] table = board.split(",");
        if (first.length != 2 || second.length != 2) {
            throw new IllegalArgumentException();
        }

        if (!duplicateTest(table, first, second)) {
            throw new IllegalArgumentException();
        }


        double resultFirst = getResult(first, table);
        double resultSecond = getResult(second, table);

        if (resultFirst == resultSecond) {
            LinkedList<String> cardOnBoard = new LinkedList<>(Arrays.asList(table));
            sort(cardOnBoard);
            LinkedList<String> cardFirstPlayer = new LinkedList<>(Arrays.asList(first));
            sort(cardFirstPlayer);
            LinkedList<String> cardSecondPlayer = new LinkedList<>(Arrays.asList(second));
            sort(cardSecondPlayer);
            int maxRankOnBoard = getLevelCard(cardOnBoard.get(cardOnBoard.size() - 1));

            int maxRankOnFirst = getLevelCard(cardFirstPlayer.get(cardFirstPlayer.size() - 1));

            int maxRankOnSecond = getLevelCard(cardSecondPlayer.get(cardSecondPlayer.size() - 1));

            if (maxRankOnBoard > maxRankOnFirst && maxRankOnBoard > maxRankOnSecond) {
                return null;
            }

            if (maxRankOnFirst > maxRankOnSecond) {
                return firstPlayer;
            } else {
                return secondPlayer;
            }
        } else {
            if (resultFirst > resultSecond) {
                return firstPlayer;
            } else {
                return secondPlayer;
            }
        }
    }

    private double getResult(String[] player, String[] table) {
        LinkedList<String> cards = new LinkedList<>();
        cards.addAll(Arrays.asList(player));
        cards.addAll(Arrays.asList(table));
        sort(cards);

        Set<String> test = new HashSet<>(cards);
        if (test.size() != cards.size()) {
            throw new IllegalArgumentException();
        }


        if (isFiveCardsHaveOrder(cards)) {
            //есть порядок
            LinkedList<String> orderCards = getFiveOrdersCard(cards);
            if (isFiveCardsHaveOneType(orderCards)) {
                //Есть порядок и цвет
                if (getLevelCard(orderCards.get(4)) == 14) {
                    //порядок и цвет старшая ТУЗ
                    return 10; //Флеш Рояль
                } else {
                    //порядок и цвет
                    return 9; //Стрит флеш
                }
            } else {
                //Нет цвета есть порядок
                return 5; //стрит
            }
        }


        if (isFourCardHaveOneLevel(cards)) {
            //Каре
            return 8;
        }


        if (isTreeAndTwoCardHaveOneLevel(cards)) {
            //Фул Хаус 3 и 2 карты.
            return 7;
        }

        if (isFiveCardsHaveOneType(cards)) {
            //Флеш просто 5 карт одной масти
            return 6;
        }

        if (isTreeCardHaveOneLevel(cards)) {
            //Сет
            return 4;
        }

        if (isTwoAndTwoCardHaveOneLevel(cards)) {
            //две пары
            return 3;
        }

        if (isTwoCardHaveOneLevel(cards)) {
            //Пара
            return 2;
        }

        int maxLevel = 0;
        for (String card : cards) {
            int level = getLevelCard(card);
            if (maxLevel < level) {
                maxLevel = level;
            }
        }

        return maxLevel * 0.1;
    }


    private boolean isFiveCardsHaveOrder(LinkedList<String> cards) {
        //sorted card
        int count = 0;
        int levelOld = getLevelCard(cards.get(0));
        for (String card : cards) {
            if (count > 4) {
                return true;
            }

            int levelNext = getLevelCard(card);
            if ((levelNext - levelOld) == 1) {

                count++;
            } else {
                count = 1;
            }
            levelOld = levelNext;
        }

        if (count > 4) {
            return true;
        }

        if (cardsHaveAce(cards)) {
            return getResultOfWithOppositeAce(cards);
        }
        return false;
    }


    private boolean isFiveCardsHaveOneType(LinkedList<String> cards) {
        HashMap<Character, Integer> charMap = new HashMap<>();
        char[] types = new char[]{'c', 'd', 'h', 's'};
        for (char ch : types) {
            charMap.put(ch, 0);
        }
        for (String card : cards) {
            char type = card.charAt(card.length() - 1);
            int value = charMap.get(type);
            value++;
            charMap.put(type, value);
        }

        for (Integer integer : charMap.values()) {
            if (integer > 4) {
                return true;
            }
        }

        return false;
    }

    private boolean isTreeCardHaveOneLevel(LinkedList<String> cards) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (String card : cards) {
            int level = getLevelCard(card);
            if (integerMap.containsKey(level)) {
                int count = integerMap.get(level);
                count++;
                integerMap.put(level, count);
            } else {
                integerMap.put(level, 1);
            }
        }

        for (Integer count : integerMap.values()) {
            if (count > 2) {
                return true;
            }
        }
        return false;
    }


    private boolean isFourCardHaveOneLevel(LinkedList<String> cards) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (String card : cards) {
            int level = getLevelCard(card);
            if (integerMap.containsKey(level)) {
                int count = integerMap.get(level);
                count++;
                integerMap.put(level, count);
            } else {
                integerMap.put(level, 1);
            }
        }

        for (Integer count : integerMap.values()) {
            if (count > 3) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoCardHaveOneLevel(LinkedList<String> cards) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (String card : cards) {
            int level = getLevelCard(card);
            if (integerMap.containsKey(level)) {
                int count = integerMap.get(level);
                count++;
                integerMap.put(level, count);
            } else {
                integerMap.put(level, 1);
            }
        }

        for (Integer count : integerMap.values()) {
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isTreeAndTwoCardHaveOneLevel(LinkedList<String> cards) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (String card : cards) {
            int level = getLevelCard(card);
            if (integerMap.containsKey(level)) {
                int count = integerMap.get(level);
                count++;
                integerMap.put(level, count);
            } else {
                integerMap.put(level, 1);
            }
        }

        int levelFirst = 0;
        for (Integer level : integerMap.keySet()) {
            int count = integerMap.get(level);
            if (count > 2) {
                levelFirst = level;
            }
        }
        if (levelFirst == 0) {
            return false;
        }

        for (Integer level : integerMap.keySet()) {
            if (level == levelFirst) {
                continue;
            }
            int count = integerMap.get(level);
            if (count > 1) {
                return true;
            }
        }
        return false;
    }

    private boolean isTwoAndTwoCardHaveOneLevel(LinkedList<String> cards) {
        HashMap<Integer, Integer> integerMap = new HashMap<>();
        for (String card : cards) {
            int level = getLevelCard(card);
            if (integerMap.containsKey(level)) {
                int count = integerMap.get(level);
                count++;
                integerMap.put(level, count);
            } else {
                integerMap.put(level, 1);
            }
        }

        int levelFirst = 0;
        for (Integer level : integerMap.keySet()) {
            int count = integerMap.get(level);
            if (count > 1) {
                levelFirst = level;
            }
        }

        for (Integer level : integerMap.keySet()) {
            if (level == levelFirst) {
                continue;
            }
            int count = integerMap.get(level);
            if (count > 1) {
                return true;
            }
        }
        return false;
    }


    private void sort(LinkedList<String> cards) {
        Collections.sort(cards, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int level1 = getLevelCard(o1);
                int level2 = getLevelCard(o2);
                return level1 - level2;
            }
        });


    }

    private int getLevelCard(String card) {
        String cardRank = card.substring(0, card.length() - 1);
        if (cardRank.equals("J")) {
            return 11;
        }
        if (cardRank.equals("Q")) {
            return 12;
        }
        if (cardRank.equals("K")) {
            return 13;
        }
        if (cardRank.equals("A")) {
            return 14;
        }
        return Integer.parseInt(cardRank);
    }

    private LinkedList<String> getFiveOrdersCard(LinkedList<String> cards) {
        LinkedList<String> fiveCard = new LinkedList<>();
        int levelOld = getLevelCard(cards.get(0));
        for (String card : cards) {
            if (card.equals(cards.get(0))) {
                fiveCard.add(card);
                continue;
            }

            int levelNext = getLevelCard(card);
            if ((levelNext - levelOld) == 1
                    || (levelNext - levelOld) == 0) {
                fiveCard.add(card);
            } else {
                fiveCard.clear();
                fiveCard.add(card);
            }
            levelOld = levelNext;

            if (fiveCard.size() > 4) {
                return fiveCard;
            }
        }
        return getNewCardWithOppositeAce(cards);
    }

    private boolean duplicateTest(String[] table, String[] first, String[] second) {
        LinkedList<String> cardOnBoard = new LinkedList<>(Arrays.asList(table));
        LinkedList<String> cardFirstPlayer = new LinkedList<>(Arrays.asList(first));
        LinkedList<String> cardSecondPlayer = new LinkedList<>(Arrays.asList(second));
        LinkedList<String> cards = new LinkedList<>(cardOnBoard);
        cards.addAll(cardFirstPlayer);
        cards.addAll(cardSecondPlayer);

        Set<String> test = new HashSet<>(cards);
        if (test.size() != cards.size()) {
            return false;
        }
        return true;
    }

    private boolean cardsHaveAce(LinkedList<String> cards) {
        for (String card : cards) {
            int level = getLevelCard(card);
            if (level == 14) {
                return true;
            }
        }
        return false;
    }

    private boolean getResultOfWithOppositeAce(LinkedList<String> cards) {
        LinkedList<String> newCards = new LinkedList<>(cards);
        for (String card : newCards) {
            int rank = getLevelCard(card);
            if (rank == 14) {
                char type = card.charAt(1);
                newCards.remove(card);
                newCards.add("1" + type);
                break;
            }
        }
        sort(newCards);
        return isFiveCardsHaveOrder(newCards);
    }

    private LinkedList<String> getNewCardWithOppositeAce(LinkedList<String> cards) {
        LinkedList<String> newCards = new LinkedList<>(cards);
        for (String card : newCards) {
            int rank = getLevelCard(card);
            if (rank == 14) {
                char type = card.charAt(1);
                newCards.remove(card);
                newCards.add("1" + type);
                break;
            }
        }
        sort(newCards);
        return newCards;
    }
}
