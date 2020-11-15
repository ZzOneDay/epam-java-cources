package com.epam.university.java.core.task060;

import java.util.LinkedList;

public class CacheImpl implements Cache {
    private int size;
    private LinkedList<Node> caches = new LinkedList<>();

    CacheImpl(int size) {
        this.size = size;
    }

    @Override
    public String getNode(int key) {
        for (Node node : caches) {
            if (node.getKey() == key) {
                return node.getValue();
            }
        }
        return "0";
    }

    @Override
    public void setNode(int key, String value) {
        for (Node node : caches) {
            if (node.getKey() == key) {
                caches.remove(node);
                break;
            }
        }

        Node node = new NodeImpl();
        node.setKey(key);
        node.setValue(value);
        caches.add(node);

        if (caches.size() > size) {
            caches.remove(0);
        }
    }
}
