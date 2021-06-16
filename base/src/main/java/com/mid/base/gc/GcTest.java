package com.mid.base.gc;

import com.google.common.base.internal.Finalizer;

import java.lang.ref.Reference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 -verbose:gc -Xms4M -Xmx4M -Xmn1M -XX:SurvivorRatio=8 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC
 *
 -XX:+PrintGC                           输出GC日志
 -XX:+PrintGCDetails                    输出GC的详细日志
 -XX:+PrintGCTimeStamps                 输出GC的时间戳（以基准时间的形式）
 -XX:+PrintGCDateStamps                 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
 -XX:+PrintTenuringDistribution         输出显示在survivor空间里面有效对象不同年龄的分布情况
 -XX:+PrintGCApplicationStoppedTime     打印垃圾回收期间程序暂停的时间.可与上面混合使用
 -XX:+PrintGCApplicationConcurrentTime  打印每次垃圾回收前,程序未中断的执行时间.可与上面混合使用
 -XX:+PrintHeapAtGC                     在进行GC的前后打印出堆的信息
 -Xloggc:/Users/jiangshaoyue/Desktop/gclog.log 日志文件的输出路径
 */
public class GcTest {

    public static void main(String[] args) {

        Queue objCache =  new ConcurrentLinkedDeque<>();
        for( ;; ){
            objCache.add(new Object());
        }
    }
}
