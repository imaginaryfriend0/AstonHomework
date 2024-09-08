import task1.Bowl;
import task1.Cat;
import task2.*;

public class Main {
    public static void main(String[] args) {
        //task #1
        Bowl bowl = new Bowl(25);
        bowl.addFood(25);
        Cat[] cats = new Cat[5];
        cats[0] = new Cat("Белый", 10);
        cats[1] = new Cat("Рыжий", 12);
        cats[2] = new Cat("Черный", 13);
        cats[3] = new Cat("Пятнистый", 14);
        cats[4] = new Cat("Полосатый", 15);

        for (Cat cat : cats) {
            cat.eat(bowl);
        }
        System.out.println(bowl.getFood());

        //task #2
        Figure[] figures = new Figure[3];
        Circle circle = new Circle(5);
        circle.setBorderColor("green");
        circle.setFillColor("red");

        Rectangle rectangle = new Rectangle(4, 4);
        rectangle.setBorderColor("black");
        rectangle.setFillColor("white");

        Triangle triangle = new Triangle(1, 2, 3);
        rectangle.setBorderColor("blue");
        rectangle.setFillColor("yellow");

        figures[0] = circle;
        figures[1] = rectangle;
        figures[2] = triangle;

        for (Figure figure : figures) {
            figureInfo(figure);
        }

    }

    public static void figureInfo(Figure figure) {
        if (figure instanceof Colorable) {
            Colorable colorable = (Colorable) figure;
            System.out.println("[" + figure.calculatePerimeter() + ", " + figure.calculateArea() + ", " + colorable.getBorderColor() + ", " + colorable.getFillColor() + "]");

        }
    }
}