package priority;

public class PriorityExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        // Создаем и запускаем потоки
        Thread[] threads = new Thread[3];
        threads[0] = new MinPriorityThread();
        threads[1] = new NormPriorityThread();
        threads[2] = new MaxPriorityThread();
        for (int i = 0; i < 3; i++) {
            switch (i) {
                case 0: threads[i].setPriority(Thread.MIN_PRIORITY); threads[i].setName("MIN_PRIORITY"); break;
                case 1: threads[i].setPriority(Thread.NORM_PRIORITY); threads[i].setName("NORM_PRIORITY"); break;
                case 2: threads[i].setPriority(Thread.MAX_PRIORITY); threads[i].setName("MAX_PRIORITY"); break;
            }
            threads[i].start();
        }
        // Ждем завершения всех потоков, давая им время на выполнение
        while (threads[0].isAlive() || threads[1].isAlive() || threads[2].isAlive()) {
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
