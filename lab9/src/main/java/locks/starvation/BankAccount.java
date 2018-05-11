package locks.starvation;

public class BankAccount {
    private double balance;
    int id;

    BankAccount(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    synchronized double getBalance() {
        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {}
        return balance;
    }

    synchronized void withdraw(double amount) {
        balance -= amount;
    }

    synchronized void deposit(double amount) {
        balance += amount;
    }

    synchronized void transfer(BankAccount to, double amount) {
        withdraw(amount);
        to.deposit(amount);
    }

    public static void main(String[] args) {
        final BankAccount fooAccount = new BankAccount(1, 500d);
        final BankAccount barAccount = new BankAccount(2, 500d);

        Thread balanceMonitorThread1 = new Thread(new BalanceMonitor(fooAccount), "BalanceMonitor");
        Thread transactionThread1 = new Thread(new Transaction(fooAccount, barAccount, 250d), "Transaction-1");
        Thread transactionThread2 = new Thread(new Transaction(fooAccount, barAccount, 250d), "Transaction-2");

        balanceMonitorThread1.setPriority(Thread.MAX_PRIORITY);
        transactionThread1.setPriority(Thread.MIN_PRIORITY);
        transactionThread2.setPriority(Thread.MIN_PRIORITY);

        balanceMonitorThread1.start();

        try {
            Thread.sleep(100l);
        } catch (InterruptedException e) {}
        transactionThread1.start();
        transactionThread2.start();

    }

}