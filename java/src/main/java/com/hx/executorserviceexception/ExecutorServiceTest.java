package com.hx.executorserviceexception;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by testuser on 16-9-18.
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {

                        System.out.println("!:UncaughtExtceptionHandler catch Exception");
                        //e.printStackTrace();

                    }
                });
                return t;
            }
        }) {


            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                FutureTask thread = null;
                if (r instanceof FutureTask) {
                    thread = (FutureTask) r;
                }
                System.out.println("end one " + (thread == null ? null : thread.isDone()) + " " + (t == null ? null : t.getMessage()));
            }
        };
        List<Future<String>> resultList = new ArrayList<Future<String>>();

        // 创建10个任务并执行
        for (int i = 0; i < 10; i++) {
            // 使用ExecutorService执行Callable类型的任务，并将结果保存在future变量中
            Future<String> future = executorService.submit(new TaskWithResult(i));
            // 将任务执行结果存储到List中
            resultList.add(future);
        }
        executorService.shutdown();

        // 遍历任务的结果
        for (Future<String> fs : resultList) {
            try {
                System.out.println(fs.get()); // 打印各个线程（任务）执行的结果
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                executorService.shutdownNow();
                e.printStackTrace();
            }
        }
    }
}

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，则该方法自动在一个线程上执行。
     *
     * @return
     * @throws Exception
     */
    public String call() throws Exception {
        System.out.println("call()方法被自动调用,干活！！！             " + Thread.currentThread().getName());
        if (Thread.currentThread().getName().equals("Thread-0"))
            throw new TaskException("Meet error in task." + Thread.currentThread().getName());
        else {
            System.out.println(Thread.currentThread().getName() + " pass ");
        }
        // 一个模拟耗时的操作
        for (int i = 999999999; i > 0; i--)
            ;
        return "call()方法被自动调用，任务的结果是：" + id + "    " + Thread.currentThread().getName();
    }
}

class TaskException extends Exception {
    public TaskException(String message) {
        super(message);
    }
}
