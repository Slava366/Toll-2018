package locks.starvation;

class Transaction implements Runnable {
    private BankAccount sourceAccount, destinationAccount;
    private double amount;

    Transaction(BankAccount sourceAccount, BankAccount destinationAccount, double amount) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.amount = amount;
    }

    public void run() {
        System.out.format("%s начало работы%n", Thread.currentThread().getName());
        sourceAccount.transfer(destinationAccount, amount);
        System.out.printf("%s конец работы%n", Thread.currentThread().getName());
    }

}