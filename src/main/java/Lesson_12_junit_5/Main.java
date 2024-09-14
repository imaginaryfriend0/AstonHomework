package Lesson_12_junit_5;

public class Main {
    public static void main(String[] args) {
        System.out.println(factorial(10));
    }

    public static int factorial(int number) {
        int value = 1;

        if (number < 0) {
            throw new IllegalArgumentException("Введите неотрицательное число");
        }

        for (int i = 1; i <= number; i++) {
            value *= i;
        }
        return value;
    }
}
