package com.epam.university.java.core.task032;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CountingProxyImpl implements CountingProxy {
    private HashMap<String, Integer> mapMethods = new HashMap<>();
    private SomeActionExecutor someActionExecutor;

    CountingProxyImpl(SomeActionExecutor someActionExecutor) {
        this.someActionExecutor = someActionExecutor;
    }

    @Override
    public int getInvocationsCount(String methodName) {
        return mapMethods.get(methodName);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (mapMethods.containsKey(method.getName())) {
            int countDouble = mapMethods.get(method.getName());
            countDouble++;
            mapMethods.put(method.getName(), countDouble);
        } else {
            mapMethods.put(method.getName(), 1);
        }

        return method.invoke(someActionExecutor, args);
    }
}
