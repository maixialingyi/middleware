package com.mid.base.threadModule.Thread;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) throws Exception {
        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                Thread.sleep(1000);
                System.out.println("callable");
                return "success";
            }
        };

        FutureTask future = new FutureTask(callable);
        Thread thread = new Thread(future);
        thread.start();

        System.out.println("mian");
    }
}
