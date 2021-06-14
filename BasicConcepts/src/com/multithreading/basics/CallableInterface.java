package com.multithreading.basics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableInterface {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<Future<String>> list = new ArrayList<>();
        for (int i=0;i<5;i++) {
            Future<String> future = executorService.submit(new CallableProcessor(i+1));
            list.add(future);
        }

        for (Future<String> f : list) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
        System.out.println("Current thread: " + Thread.currentThread().getName());
    }
}

class CallableProcessor implements Callable<String> {
    private int id;

    public CallableProcessor(int id) {
        this.id = id;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id: "+id;
    }
}
