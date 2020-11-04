package com.epam.university.java.core.task050;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class Task050Impl implements Task050 {
    @Override
    public double calculate(int size, Map<Double, Double> items) {
        if (size == 0 || items == null || items.size() == 0) {
            throw new IllegalArgumentException();
        }

        double freeSize = size;

        List<Item> itemList = new LinkedList<>();
        double mainCost = 0;

        for (Double cost : items.keySet()) {
            Item item = new Item(cost, items.get(cost));
            itemList.add(item);
        }

        Collections.sort(itemList);

        for (Item item : itemList) {
            double countItems = item.getAmount(freeSize);
            mainCost += item.cost * countItems;
            freeSize -= countItems;
        }

        int main = (int) (mainCost * 10000);
        main = (int) Math.round(main / 10.0);
        return main / 1000.0;
    }

    private static class Item implements Comparable {
        Double cost;
        Double weight;
        Double k;

        Item(Double cost, Double weight) {
            this.cost = cost;
            this.weight = weight;
            k = cost / weight;
            this.cost = cost / weight;
        }

        double getAmount(double freeWeight) {
            if (freeWeight > weight) {
                return weight;
            } else {
                weight = weight - freeWeight;
                return freeWeight;
            }
        }


        @Override
        public int compareTo(Object o) {
            Item otherItem = (Item) o;
            if (k > otherItem.k) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}
