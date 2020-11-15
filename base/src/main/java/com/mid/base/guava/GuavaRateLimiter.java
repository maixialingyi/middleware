package com.mid.base.guava;

import com.google.common.util.concurrent.RateLimiter;

public class GuavaRateLimiter {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);

        while (true) {
            System.out.println("get 1 tokens: " + rateLimiter.acquire() + "s");
        }
    }
}
