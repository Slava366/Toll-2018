package bad;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Должен выводить в консоль "Помиловать" 1000 раз, однако это происходит не всегда.
 * 7-й вариант, дожидаемся завершения нити корректора:
 * Результат: Проблема решена на 100%.
 */
public class BadThreads {

    private static String message;

    private static class CorrectorThread extends Thread {
        public void run() {
            message = "Помиловать";
        }
    }

    public static void main(String args[]) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread();
            message = "Казнить";
            correctorThread.start();
            correctorThread.join();
            if (message.equalsIgnoreCase("Казнить")) System.out.println(message);
        }
    }
}