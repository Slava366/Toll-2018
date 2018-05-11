package states;

public class ThirdThread extends Thread {
    @Override
    public void run() {
        super.run();
        try {
            synchronized (this) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
