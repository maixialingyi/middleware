package com.mid.base.threadModule.Lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author jiangshaoyue
 * @Date 2019/5/27 15:18
 */
public class ReentrantLockTest {

    public void handler(){
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String args[]) {
        final ReentrantLockTest reentrantLockTest = new ReentrantLockTest();
        for (int i = 0; i < 100000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantLockTest.handler();
                }
            }, "threadName" + 1).start();
        }
    }
}
