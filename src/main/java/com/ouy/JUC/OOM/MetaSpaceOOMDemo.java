package com.ouy.JUC.OOM;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaSpaceOOMDemo {
    static class OOMTest{}
    public static void main(String[] args) {
        int i=0;
        try {
            while (true){
                i++;
                Enhancer enhancer =  new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return method.invoke(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Throwable t){
            System.out.println("***********经历了多少次后发生了异常："+i);
            t.printStackTrace();
        }

    }
}
