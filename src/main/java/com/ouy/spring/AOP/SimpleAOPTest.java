package com.ouy.spring.AOP;

public class SimpleAOPTest {
    public static void main(String[] args) {
        MethodInvocation logTak = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        Advice beforAdvice = new BeforAdvice(helloServiceImpl, logTak);

        HelloService proxy = (HelloService)SimpleAOP.getProxy(helloServiceImpl, beforAdvice);
        proxy.sayHelloWorld();
    }
}
