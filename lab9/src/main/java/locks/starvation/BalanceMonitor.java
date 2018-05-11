package locks.starvation;

class BalanceMonitor implements Runnable {
    private BankAccount account;
    BalanceMonitor(BankAccount account) { this.account = account;}
    boolean alreadyNotified = false;

    @Override
    public void run() {
        System.out.format("%s начало работы%n", Thread.currentThread().getName());
        while (true) {
            if(account.getBalance() <= 0) {
                // Завершаем работу
                System.out.format("%s : баланс меньше 0%n", Thread.currentThread().getName());
                break;
            }
        }
        System.out.format("%s : завершаем работу", Thread.currentThread().getName());
    }

}
