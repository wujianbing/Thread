import java.util.concurrent.CountDownLatch;

public class Main {

    static /*volatile*/ CountDownLatch countDownLatch = new CountDownLatch(5);
    static CountDownLatch getCountDownLatch(){
        return countDownLatch;
    }

    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            new Thread(()->{
                CountDownLatch countDownLatch = Main.getCountDownLatch();
                System.out.println("线程执行-"+ Thread.currentThread().getName());
                System.out.println(countDownLatch.getCount());
                countDownLatch.countDown();

                /*try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            },"thread-"+i).start();
        }

        new Thread(()->{
            CountDownLatch countDownLatch = Main.getCountDownLatch();
            try {
                countDownLatch.await();
                System.out.println("解锁了............");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
