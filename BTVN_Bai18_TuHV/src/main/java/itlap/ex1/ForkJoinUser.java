package itlap.ex1;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinUser {

    public static void main(String[] args) {
        long start = System.nanoTime();
        ForkJoinFibonacci task = new ForkJoinFibonacci(50);
        new ForkJoinPool().invoke(task);

        System.out.println(task.getNumber());
        long end = System.nanoTime();
        System.out.println("Time  : " + (end - start)/1000000000 + "  seconds");

    }
}