package com.ouy.spring.IOC;


public class SimpleIocTest {



    public static void main(String[] args) throws Exception{
//        String url = SimpleIOC.class.getClassLoader().getResource("D:\\OUY\\MyProject\\MyJUC\\src\\main\\java\\com\\ouy\\spring\\IOC\\ioc.xml").getFile();
        SimpleIOC bf = new SimpleIOC("D:\\OUY\\MyProject\\MyJUC\\src\\main\\java\\com\\ouy\\spring\\IOC\\ioc.xml");

        Wheel wheel = (Wheel) bf.getBean("wheel");
        System.out.println(wheel);

        Car car = (Car)bf.getBean("car");
        System.out.println(car);
    }
}
