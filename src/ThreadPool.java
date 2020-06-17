import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    LinkedBlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5,1000, TimeUnit.MILLISECONDS,blockingQueue);
    CountDownLatch latch = new CountDownLatch(5);
    public String read(){
        StringBuilder builder = new StringBuilder();
        Thread thread1 = new Thread(() -> {
            System.out.println("1");
            builder.append("1");
            latch.countDown();
        });
        Thread thread2 = new Thread(()->{
           System.out.println("2");
            builder.append("2");
            latch.countDown();
       });
        Thread thread3 = new Thread(()->{
           System.out.println("3");
            builder.append("3");
            latch.countDown();
       });
        Thread thread4 = new Thread(()->{
           System.out.println("4");
            builder.append("4");
            latch.countDown();
       });
        Thread thread5 = new Thread(()->{
           System.out.println("5");
            builder.append("5");
            latch.countDown();
       });
       poolExecutor.execute(thread1);
       poolExecutor.execute(thread2);
       poolExecutor.execute(thread3);
       poolExecutor.execute(thread4);
       poolExecutor.execute(thread5);

        try {
            latch.await();
            System.out.println(builder.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "1";
    }

    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool();
        pool.read();
    }
}
