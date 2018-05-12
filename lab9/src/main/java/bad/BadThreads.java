package bad;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Должен выводить в консоль "Помиловать" 1000 раз, однако это происходит не всегда.
 * 5-й вариант, используем lock:
 * Результат: Проблема не решена.
 */
public class BadThreads {

    static String message;
    final static Object lock = new Object();
    public final static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static class CorrectorThread extends Thread {
        public void run() {
            BadThreads.readWriteLock.writeLock().lock();
            try {
                message = "Помиловать";
            } finally {
                BadThreads.readWriteLock.writeLock().unlock();
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread();
            BadThreads.readWriteLock.writeLock().lock();
            try {
                message = "Казнить";
            } finally {
                BadThreads.readWriteLock.writeLock().unlock();
            }
            correctorThread.start();
            Thread.sleep(10);
            BadThreads.readWriteLock.readLock().lock();
            try {
                if (message.equalsIgnoreCase("Казнить")) System.out.println(message);
            } finally {
                BadThreads.readWriteLock.readLock().unlock();
            }
        }
    }
}