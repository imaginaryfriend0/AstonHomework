package Lesson_10;

public class MyArraySizeException extends Exception {
    public MyArraySizeException() {
        super("Некорректный размер массива, ожидалось: 4х4");
    }
}
