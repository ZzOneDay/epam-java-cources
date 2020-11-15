package com.epam.university.java.core.task062;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class Task062Impl implements Task062 {
    @Override
    public OutputStream objectSerialization(Object obj) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(buffer)) {
            oos.writeObject(obj);
            oos.flush();
            buffer.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error objectSerialization");
        }
        return buffer;
    }

    @Override
    public Object objectDeserialization(OutputStream outStream) {
        try {
            ByteArrayOutputStream buffer = (ByteArrayOutputStream) outStream;
            buffer.toByteArray();
            ByteArrayInputStream in = new ByteArrayInputStream(buffer.toByteArray());
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            Object object = objectInputStream.readObject();
            if (object instanceof SingletonObject) {
                object = SingletonObject.getInstance();
            }
            return object;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error objectDeserialization");
        }
    }
}
