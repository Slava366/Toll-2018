package yield;

public class YieldExample {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        Thread secondThread = new Thread(new YieldThread());
        secondThread.start();   // Создаем второй поток
        for (int i = 0; i < 50; i++) {
            if (secondThread.isAlive()) Thread.yield(); // Если второй поток еще жив, выделяем ему время
            System.out.println(Thread.currentThread().getName() + "- досчитал до:" + i);
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
