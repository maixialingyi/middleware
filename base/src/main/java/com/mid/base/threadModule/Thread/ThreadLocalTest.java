package com.mid.base.threadModule.Thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalTest {
    private static ThreadLocal threadLocal = new ThreadLocal();
    private static ThreadLocal inthreadLocal = new InheritableThreadLocal();

    static class MyThread extends Thread{
        @Override
        public void run() {
            System.out.println(threadLocal.get());
            System.out.println(inthreadLocal.get());
            new MyThread().run();
        }
    }
    public static void main(String[] args) {
        threadLocal.set("parent");
        //inthreadLocal.set("parent");

        new MyThread().run();


    }
}
