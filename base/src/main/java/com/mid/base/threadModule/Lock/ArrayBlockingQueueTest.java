package com.mid.base.threadModule.Lock;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(1);
        queue.add(new Object());
        queue.take();
    }
}
