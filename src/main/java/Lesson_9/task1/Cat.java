package Lesson_9.task1;

public class Cat extends Animal {
    static int catCount;
    private String name;
    private int appetite;
    private boolean isHungry;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isHungry = true;
        catCount++;
    }

    public Cat() {
        catCount++;
    }

    public static void getCats() {
        System.out.println("Cats total: " + catCount + ".");
    }

    @Override
    public void run(double distance) {
        if (distance > 200) {
            System.out.println("Cats don't run further than 200m.");
        } else {
            System.out.println("Cat ran: " + distance + "m.");
        }
    }

    @Override
    public void swim(double distance) {
        System.out.println("Cats don't swim.");
    }

    public void eat(Bowl bowl) {
        if (isHungry) {
            if (bowl.getFood() >= appetite) {
                bowl.decreaseFood(appetite);
                isHungry = false;
                System.out.println(name + " ate and is now full");
            } else {
                System.out.println(name + " couldn't eat - not enough food in the bowl");
            }
        } else {
            System.out.println(name + " is already full.");
        }
    }

    public boolean isFull() {
        return !isHungry;
    }

    public String getName() {
        return name;
    }
}
