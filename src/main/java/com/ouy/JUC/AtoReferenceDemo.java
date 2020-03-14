package com.ouy.JUC;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User{
    String name;
    int age;
}

public class AtoReferenceDemo {
    public static void main(String[] args) {
        User zhang = new User("zhangshan",12);
        User li = new User("lisi",14);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(zhang);
        System.out.println(atomicReference.compareAndSet(zhang,li)+"\t"+atomicReference.get());
    }
}
