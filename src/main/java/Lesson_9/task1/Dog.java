package Lesson_9.task1;

public class Dog extends Animal {
    static int dogCount;

    public Dog() {
        dogCount++;
    }

    public static void getDogs() {
        System.out.println("Dogs total: " + dogCount + ".");
    }

    @Override
    public void run(double distance) {
        if (distance > 500) {
            System.out.println("Dogs don't run further than 500m.");
        } else {
            System.out.println("Dog ran: " + distance + "m.");
        }
    }

    @Override
    public void swim(double distance) {
        if (distance > 10) {
            System.out.println("Dogs don't swim further than " + distance + "m.");
        } else {
            System.out.println("Dog swam " + distance + "m.");
        }
    }
}
