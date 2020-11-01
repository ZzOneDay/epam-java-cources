package com.epam.university.java.core.task041;

import java.util.Collection;

public class Task041Impl implements Task041 {
    private int code = 0;

    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }
        Entity entity = new EntityImpl(code, value);
        collection.add(entity);
        code++;
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }

        for (Entity oneEntity : collection) {
            if (oneEntity.equals(entity)) {
                return entity;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }

        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }

        for (Entity oneEntity : collection) {
            if (oneEntity.equals(entity)) {
                oneEntity.getValue();
                EntityImpl entity1 = (EntityImpl) oneEntity;
                entity1.setValue(value);
            }
        }
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }

        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        collection.remove(entity);
    }
}
