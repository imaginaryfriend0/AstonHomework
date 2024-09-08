package task1;

public class Cat extends Animal {
    private String name;
    private int appetite;
    private boolean isHungry;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.isHungry = true;
    }

    public Cat() {
        catCount++;
    }

    @Override
    public void run(double distance) {
        if (distance > 200) {
            System.out.println("Коты не бегают дальше 200 м.");
        } else {
            System.out.println("Кот пробежал " + distance + " м.");
        }
    }

    @Override
    public void swim(double distance) {
        System.out.println("Коты не плавают.");
    }

    public void eat(Bowl bowl) {
        if (isHungry) {
            if (bowl.getFood() >= appetite) {
                bowl.decreaseFood(appetite);
                isHungry = false;
                System.out.println(name + " покушал и теперь сыт.");
            } else {
                System.out.println(name + " не смог покушать, в миске недостаточно еды.");
            }
        } else {
            System.out.println(name + " уже сыт.");
        }
    }

    public boolean isFull() {
        return !isHungry;
    }

    public String getName() {
        return name;
    }
}
