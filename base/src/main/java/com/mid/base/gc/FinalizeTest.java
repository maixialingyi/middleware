package com.mid.base.gc;

public class FinalizeTest {

    public static void main(String[] args){

        for(int i = 0 ; i< 1000; i++){
            Person p = new Person();
        }
        System.gc();//增加垃圾回收器启动的概率
    }
}

class Person {
    //对象被回收时调用finalize()
    protected void finalize() throws Throwable{
        System.out.println("调用finalize()！！！");
    }
}
