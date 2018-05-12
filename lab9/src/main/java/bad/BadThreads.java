package bad;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Должен выводить в консоль "Помиловать" 1000 раз, однако это происходит не всегда.
 * 6-й вариант, используем synchronized:
 * Результат: Проблема не решена.
 */
public class BadThreads {

    private static String message;

    private static synchronized void setMessage(String message) {
        BadThreads.message = message;
    }

    private static synchronized String getMessage(){
        return BadThreads.message;
    }

    private static class CorrectorThread extends Thread {
        public void run() {
            setMessage("Помиловать");
        }
    }

    public static void main(String args[]) throws InterruptedException {
        for (int i=0; i<1000; i++) {
            CorrectorThread correctorThread = new CorrectorThread();
            setMessage("Казнить");
            correctorThread.start();
            Thread.sleep(10);
            if (getMessage().equalsIgnoreCase("Казнить")) System.out.println(getMessage());
        }
    }
}