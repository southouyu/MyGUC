package com.ouy.spring.AOP;

import java.lang.reflect.Method;

public class BeforAdvice implements Advice {

    private Object bean;
    private MethodInvocation methodInvocation;

    public BeforAdvice(Object bean, MethodInvocation methodInvocation){
        this.bean = bean;
        this.methodInvocation = methodInvocation;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        methodInvocation.invoke();
        return method.invoke(bean,args);
    }
}
