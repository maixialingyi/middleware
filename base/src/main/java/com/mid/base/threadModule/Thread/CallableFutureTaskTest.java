package com.mid.base.threadModule.Thread;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

public class CallableFutureTaskTest {

    static class Task1 implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println("Task1 call() 开始执行--");
            Thread.sleep(10000);
            if(1==1){
                throw new RuntimeException("task1子任务异常 校验失败");
            }
            System.out.println("Task1 call() 结束执行--");
            return "success";
        }
    }
    static class Task2 implements Callable{

        @Override
        public Object call() throws Exception {
            System.out.println("Task2 call() 开始执行--");
            Thread.sleep(2000);
            if(1==1){
                throw new RuntimeException("task2子任务异常 校验失败");
            }
            System.out.println("Task2 call() 结束执行--");
            return "success";
        }
    }


    public static void main(String[] args) {
        Task1 task1 = new Task1();

        FutureTask future = new FutureTask(task1);
        Task2 task2 = new Task2();
        FutureTask future2 = new FutureTask(task2);

        List<FutureTask> futureTaskList = new LinkedList<>();
        futureTaskList.add(future);
        futureTaskList.add(future2);

        /**
         * DiscardPolicy：丢弃任务，但是不抛出异常
         * DiscardOldestPolicy：丢弃队列最前面的任务，然后重新提交被拒绝的任务
         * AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
         * CallerRunsPolicy：由调用线程处理该任务,既独自创建线程不走线程池
         *
         * 使用线程池要注意：线程池使用FutureTask的时候拒绝策略设置为DiscardOldestPolicy或AbortPolicy
         * 如果线程被线程只决绝则get()将永远被阻塞  可用超时get(long time)
         */
        ExecutorService executor = Executors.newCachedThreadPool();
        for(FutureTask futureTask : futureTaskList){
            executor.execute(futureTask);
        }

        boolean br = true;
        while(br) {
            if(futureTaskList.size() == 0){
                br = false;
            }
            Iterator<FutureTask> it = futureTaskList.iterator();
            while (it.hasNext()) {
                FutureTask futureTask = it.next();
                if (futureTask.isDone()) {
                    try {
                        //调用get才会获取抛出的异常
                        String result = (String) futureTask.get();
                        if ("success".equals(result)) {
                            //移除
                            it.remove();
                            //若为空则结束
                            if(futureTaskList.size() == 0){
                                br = false;
                            }
                        } else {
                            //验证不通过  停止其他线程
                            futureTask.cancel(true);

                            br = false;
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        br = false;
                        break;
                    }
                }
            }
        }
        System.out.println("跳出循环");
        executor.shutdown();
        Thread.currentThread().interrupt();
    }
}
