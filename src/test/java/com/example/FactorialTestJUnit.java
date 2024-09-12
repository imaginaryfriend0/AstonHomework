package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.example.Main.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactorialTestJUnit {
    @Test
    public void factorialOfZeroTest() {
        assertEquals(1, factorial(0), "Факториал нуля должен быть равен единице");
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 2",
            "3, 6",
            "4, 24",
            "5, 120",
            "6, 720",
            "7, 5040",
            "8, 40320",
            "9, 362880",
            "10, 3628800"
    })
    public void factorialMainTest(int number, int expected) {
        int actual = factorial(number);
        assertEquals(actual, expected, "Неверное значение факториала для числа " + number);
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            factorial(-5);
        }, "Факториал отрицательного числа должен бросать IllegalArgumentException");
    }
}