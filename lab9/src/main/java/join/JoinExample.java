package join;

public class JoinExample {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + "- начало работы.");
        Thread secondThread = new JoinThread();
        System.out.println(Thread.currentThread().getName() + "- запуск второго потока.");
        secondThread.start();
        System.out.println(Thread.currentThread().getName() + "- ждем завершения второго потока 5 сек.");
        secondThread.join(5000/*, 200 */);
        if (secondThread.isAlive()) {
            // Второй поток еще не закончил свою работу
            System.out.println(Thread.currentThread().getName() + "- подождали 5 секунд.");
            System.out.println(Thread.currentThread().getName() + "- продолжаем работу основного потока.");
        }
        System.out.println(Thread.currentThread().getName() + "- конец работы.");
    }
}
