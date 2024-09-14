package Lesson_9.task1;

public class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public void decreaseFood(int amount) {
        if (food >= amount) {
            food -= amount;
        }
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println(amount + " units of food were added to the bowl");
    }

    public int getFood() {
        return food;
    }
}