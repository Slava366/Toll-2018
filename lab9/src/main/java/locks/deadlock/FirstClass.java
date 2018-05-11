package locks.deadlock;

class FirstClass {

    synchronized void foo(SecondClass secondClass) {

        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " вошел в метод FirstClass.foo()");

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс FirstClass прерван");
        }

        System.out.println(threadName + " пытается вызвать метод SecondClass.last()");
        secondClass.last();
    }

    synchronized void last() {
        System.out.println("В методе FirstClass.last()");
    }
}