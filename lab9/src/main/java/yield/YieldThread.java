package yield;

public class YieldThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "- досчитал до:" + i);
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
