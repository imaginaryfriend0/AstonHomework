package task1;

public class Dog extends Animal {
    public Dog() {
        dogCount++;
    }

    @Override
    public void run(double distance) {
        if (distance > 500) {
            System.out.println("Собаки не бегают дальше 500м.");
        } else {
            System.out.println("Собака пробежала " + distance + " м.");
        }
    }

    @Override
    public void swim(double distance) {
        if (distance > 10) {
            System.out.println("Собаки не плавают дальше " + distance + " м.");
        } else {
            System.out.println("Собака проплыла " + distance + " м.");
        }
    }
}
