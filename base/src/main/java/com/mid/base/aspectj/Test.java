package com.mid.base.aspectj;

public class Test {

    @MianAnnotation
    public void show(){
        System.out.println("show");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.show();
    }
}
