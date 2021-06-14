package com.learning.multithreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizeCollections {
    public static void main(String[] args) {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++)
                    list.add(i);
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<1000;i++)
                    list.add(i);
            }
        });

        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Size of list: " + list.size());
    }
}
