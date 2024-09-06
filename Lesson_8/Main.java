public class Main {
    public static void main(String[] args) {

        Employee[] employeesArray = new Employee[5];
        employeesArray[0] = new Employee("Кошкин К.К.", "Директор", "koshkinkk@mail.ru", "+7912222222", 200000, 50);
        employeesArray[1] = new Employee("Петров П.П.", "Менеджер", "petrovpp@mail.ru", "+79121112233", 100000, 40);
        employeesArray[2] = new Employee("Собакин С.С.", "Менеджер", "sobakinss@mail.ru", "+79123333333", 100000, 30);
        employeesArray[3] = new Employee("Кошкобакин К.Б.", "Тестировщик", "koshkobakinkb@mail.ru", "+79124444444", 75000, 30);
        employeesArray[4] = new Employee("Собакошкин С.К.", "Разработчик", "sobakoshkinsk@mail.ru", "+79125555555", 85000, 30);
        for (Employee employee : employeesArray) {
            employee.getEmployeeInfo();
        }

        Park park = new Park();
        park.addAttraction("Колесо", 8, 21, 150);
        park.addAttraction("Карусель", 10, 18, 250);
        for (Park.Attraction attraction : park.getAttractions()) {
            attraction.GetAttractionInfo();
        }
    }
}