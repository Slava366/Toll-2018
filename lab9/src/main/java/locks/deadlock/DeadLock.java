package locks.deadlock;

class Deadlock implements Runnable {

    FirstClass firstClass = new FirstClass();
    SecondClass secondClass = new SecondClass();

    Deadlock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        firstClass.foo(secondClass); // получить блокировку для объекта firstClass
        // в этом потоке исполнения

        System.out.println("Назад в главный поток");
    }

    @Override
    public void run() {
        secondClass.bar(firstClass); // получить блокировку для объекта secondClass
        // в другом потоке исполнения
        System.out.println("Назад в другой поток");
    }

    public static void main(String args[]) {
        new Deadlock();
    }
}