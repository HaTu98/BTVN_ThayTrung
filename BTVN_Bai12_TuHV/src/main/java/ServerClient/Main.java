package ServerClient;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        long timeOut = 5000;
        TimeOutThread timeOutThread = new TimeOutThread();
        timeOutThread.setTimeOut(timeOut);
        Thread thread = new Thread(timeOutThread);
        //start thread 1
        thread.start();
        thread.join();

        //thread 2 start
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(1000);
                    System.out.println("success");
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
    }
}