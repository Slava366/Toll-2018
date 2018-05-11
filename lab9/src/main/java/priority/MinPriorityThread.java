package priority;

public class MinPriorityThread extends Thread{
    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        for (int i = 0; i < 50; i++) {
            System.out.println(Thread.currentThread().getName() + "- досчитал до:" + i);
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
