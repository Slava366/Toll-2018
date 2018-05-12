package bad;

/**
 * Должен выводить в консоль "Помиловать" 1000 раз, однако это происходит не всегда.
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
            message = "Казнить";
            correctorThread.start();
            Thread.sleep(10);
            if (message.equalsIgnoreCase("Казнить"))
                System.out.println(message);
        }
    }
}