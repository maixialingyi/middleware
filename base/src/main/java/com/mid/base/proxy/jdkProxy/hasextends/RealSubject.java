package com.mid.base.proxy.jdkProxy.hasextends;

import com.mid.base.proxy.jdkProxy.hasextends.ISubject;

public class RealSubject implements ISubject {

    @Override
    public void pull() {
        System.out.println("pull");
    }

    @Override
    public void push() {
        System.out.println("push");
    }
}
