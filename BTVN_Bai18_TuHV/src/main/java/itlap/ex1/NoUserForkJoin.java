package itlap.ex1;

public class NoUserForkJoin {
    public static void main(String[] args) {
        long start = System.nanoTime();
        System.out.print(fibonacci(50));
        long end = System.nanoTime();
        System.out.println("Time  : " + (end - start)/1000000000 + "  seconds");
    }

    public static int fibonacci(int n) {
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

}
