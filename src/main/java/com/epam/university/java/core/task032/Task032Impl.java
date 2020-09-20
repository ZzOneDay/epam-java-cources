package com.epam.university.java.core.task032;

import java.lang.reflect.Proxy;

public class Task032Impl implements Task032 {
    private SomeActionExecutorImpl someActionExecutor = new SomeActionExecutorImpl();
    private CountingProxyImpl countingProxy = new CountingProxyImpl(someActionExecutor);

    @Override
    public CountingProxy createProxyWrapper() {
        return countingProxy;
    }

    @Override
    public SomeActionExecutor createExecutorWithProxy(CountingProxy proxy) {
        if (proxy == null) {
            throw new IllegalArgumentException();
        }
        ClassLoader classLoader = someActionExecutor.getClass().getClassLoader();
        return (SomeActionExecutor)
                Proxy.newProxyInstance(classLoader,
                        someActionExecutor.getClass().getInterfaces(),
                        countingProxy);
    }
}
