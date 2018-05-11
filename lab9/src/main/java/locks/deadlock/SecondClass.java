package locks.deadlock;

class SecondClass {

    synchronized void bar(FirstClass firstClass) {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " вошел в метод SecondClass.bar()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс SecondClass прерван");
        }

        System.out.println(threadName + " пытается вызвать метод FirstClass.last()");
        firstClass.last();
    }

    synchronized void last() {
        System.out.println("В методе SecondClass.last()");
    }
}
