package com.test.spring.aspectj;

import org.springframework.stereotype.Service;

@Service
public class AspectServiceImpl {

    @MianAnnotation
    public void nullParam() {
    }

    @MianAnnotation
    public void oneParam(User user) {
    }

    @MianAnnotation
    public void moreParam(User user, Person person) {
        System.out.println("moreParam");
    }
}
