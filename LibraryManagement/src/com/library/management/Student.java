package com.library.management;

import java.util.Random;

public class Student implements Runnable{
    private int id;
    private Book[] books;

    public Student (int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run () {
        Random random = new Random();
        while (true) {
            int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);
            try {
                books[bookId].read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString () {
        return "Student{" +
                "id=" + id +
                '}';
    }
}
