package states;

/**
 * public static enum Thread.State
 * extends Enum<Thread.State>
 *
 * A thread can be in one of the following states:
 *
 * 1 - NEW
 * Созданный поток, до вызова метода start.
 * 2 - RUNNABLE
 * После вызова метода start.
 * 3 - BLOCKED
 * Поток, который заблокирован в ожидании блокировки.
 * 4 - WAITING
 * Поток, который бесконечно ждет другого потока для выполнения определенного действия.
 * 5 - TIMED_WAITING
 * Поток ждет некоторое время окончания работы другого потока.
 * 6 - TERMINATED
 * Поток завершил свою работу.
 */
public class ThreadStates {
    public static void main(String[] args) throws InterruptedException {
        Thread secondThread = new SecondThread();
        System.out.format("%s в состоянии %s - после создания новой нити.%n", secondThread.getName(), secondThread.getState());
        secondThread.start();
        System.out.format("%s в состоянии %s - после вызова метода start.%n", secondThread.getName(), secondThread.getState());
        Thread.sleep(50);
        System.out.format("%s в состоянии %s - ждет некоторое время окончания работы другого потока.%n", secondThread.getName(), secondThread.getState());
        secondThread.join();
        System.out.format("%s в состоянии %s - завершил свою работу.%n", secondThread.getName(), secondThread.getState());

        System.out.println();

        Thread thirdThread = new ThirdThread();
        thirdThread.start();
        synchronized (thirdThread) {
            Thread.sleep(50);
            System.out.format("%s в состоянии %s - заблокирован.%n", thirdThread.getName(), thirdThread.getState());
        }
        Thread.sleep(50);
        System.out.format("%s в состоянии %s - бесконечно ждет другого потока.%n", thirdThread.getName(), thirdThread.getState());
        thirdThread.interrupt();
    }
}
