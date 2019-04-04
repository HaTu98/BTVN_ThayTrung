import java.util.concurrent.atomic.AtomicBoolean;

public class TimeOutThread implements Runnable{
    private AtomicBoolean isRunning = new AtomicBoolean(true);
    private long timeOut;

    public  void run(){
        int count = 0;

        long start = System.currentTimeMillis();
        while (isRunning.get()) {
            try {
                Thread.sleep(1000);
                count++;
                System.out.println(count);
                if(System.currentTimeMillis() > (start + timeOut)) {
                    stop();
                    break;
                }
            } catch (InterruptedException e ){
                e.printStackTrace();
            }
        }
    }

    public  void stop(){
        isRunning.set(false);
    }
    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
}