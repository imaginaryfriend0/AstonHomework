package task1;

public class Animal {
    static int animalCount;
    static int catCount;
    static int dogCount;

    public Animal() {
        animalCount++;
    }

    public void run(double distance) {
        System.out.println("Животное пробежало " + distance + " м.");
    }

    public void swim(double distance) {
        System.out.println("Животное пробежало " + distance + " м.");
    }

    public static void getAnimals() {
        System.out.println("Животных всего: " + animalCount + ",\nКошек: " + catCount + ",\nСобак: " + dogCount + ".");
    }
}
