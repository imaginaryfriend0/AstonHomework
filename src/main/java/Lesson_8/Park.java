package Lesson_8;

import java.util.ArrayList;
import java.util.List;

public class Park {
    private List<Attraction> attractions;

    public Park() {
        attractions = new ArrayList<>();
    }

    public void addAttraction(String name, int openingHour, int closingHour, double price) {
        attractions.add(new Attraction(name, openingHour, closingHour, price));
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public class Attraction {
        private String name;
        private int openingHour;
        private int closingHour;
        private double price;

        public Attraction(String name, int openingHour, int closingHour, double price) {
            this.name = name;
            this.openingHour = openingHour;
            this.closingHour = closingHour;
            this.price = price;
        }

        public void GetAttractionInfo() {
            System.out.println("Название аттракциона: " + name + ",\nРаботает с " + openingHour + " до " + closingHour + ",\nЦена: " + price + ".\n");
        }
    }
}
