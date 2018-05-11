package priority;

public class NormPriorityThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "- досчитал до:" + i);
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
