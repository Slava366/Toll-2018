package bad;

/**
 * Должен выводить в консоль "Помиловать" 1000 раз, однако это происходит не всегда.
 * 1-й вариант, поменяли строки местами:
 * correctorThread.start();
 * message = "Казнить";
 * Результат: Проблема не решилась.
 */
public class BadThreads {

    static String message;
    static Object lock = new Object();

    private static class CorrectorThread extends Thread {
        public void run() {
            message = "Помиловать";
        }
    }

    public static void main(String args[]) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread();
            correctorThread.start();
            message = "Казнить";
            Thread.sleep(10);
            if (message.equalsIgnoreCase("Казнить"))
                System.out.println(message);
        }
    }
}