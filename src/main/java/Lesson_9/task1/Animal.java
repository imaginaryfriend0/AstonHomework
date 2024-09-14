package Lesson_9.task1;

public class Animal {
    static int animalCount;

    public Animal() {
        animalCount++;
    }

    public void run(double distance) {
        System.out.println("Animal ran: " + distance + " m.");
    }

    public void swim(double distance) {
        System.out.println("Animal swam: " + distance + " m.");
    }

    public static void getAnimals() {
        System.out.println("Animals total: " + animalCount + ".");
    }
}
