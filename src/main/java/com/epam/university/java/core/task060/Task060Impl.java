package com.epam.university.java.core.task060;

public class Task060Impl implements Task060 {
    @Override
    public Cache createCache(int size) {
        return new CacheFactoryImpl().newInstance(size);
    }

    @Override
    public void set(Cache cache, int key, String value) {
        cache.setNode(key, value);
    }

    @Override
    public String get(Cache cache, int key) {
        return cache.getNode(key);
    }
}
