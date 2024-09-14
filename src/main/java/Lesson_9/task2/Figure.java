package Lesson_9.task2;

public interface Figure {
    default double calculatePerimeter() {
        throw new UnsupportedOperationException("Вычисление периметра для данной фигуры еще не реализовано.");
    }

    default double calculateArea() {
        throw new UnsupportedOperationException("Вычисление площади для данной фигуры еще не реализовано.");
    }
}
