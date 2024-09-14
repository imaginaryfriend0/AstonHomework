package Lesson_12_testng;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static Lesson_12_testng.Main.factorial;

public class FactorialTestNG {

    @Test
    public void factorialOfZeroTest() {
        assertEquals(factorial(0), 1, "Факториал нуля должен быть равен единице");
    }

    @DataProvider(name = "factorialData")
    public Object[][] factorialData() {
        return new Object[][]{
                {1, 1},
                {2, 2},
                {3, 6},
                {4, 24},
                {5, 120},
                {6, 720},
                {7, 5040},
                {8, 40320},
                {9, 362880},
                {10, 3628800}
        };
    }

    @Test(dataProvider = "factorialData")
    public void factorialMainTest(int number, int expected) {
        int actual = factorial(number);
        assertEquals(actual, expected, "Неверное значение факториала для числа " + number);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        factorial(-5);
    }
}
