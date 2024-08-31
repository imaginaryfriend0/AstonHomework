import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //printThreeWords();
        //checkSumSign();
        //printColor();
        //compareNumbers();
        //System.out.println(isSumBtwn10And20(5,10));
        //numberSignCheck(10);
        //System.out.println(isNumberNegative(10));
        //multiplyStringOutput("aqa",5);
        //System.out.println(isLeapYear(2023));
        //swapArrayValues();
        //fillArray();
        //alterArray();
        //alterArrayDiagonals();
        //createNumberArray(10,5);
    }

    public static void printThreeWords() {
        System.out.println("Orange\nBanana\nApple");
    }

    public static void checkSumSign() {
        int a = 5;
        int b = 5;
        System.out.println((a+b>=0?"Сумма положительная":"Сумма отрицательная"));
    }

    public static void printColor() {
        int value = 101;

        if (value <= 0) System.out.println("Красный");
        else if (value > 100) System.out.println("Зелёный");
        else System.out.println("Жёлтый");
    }

    public static void compareNumbers() {
        int a = 1;
        int b = 2;
        System.out.println((a>=b?"a>=b":"a<b"));
    }

    public static boolean isSumBtwn10And20(int a, int b) {
        return ((a+b >= 10) && (a+b <= 20));
    }

    public static void numberSignCheck(int a) {
        System.out.println((a>=0?"Положительное число":"Отрицательное число"));
    }

    public static boolean isNumberNegative(int a) {
        return a < 0;
    }

    public static void multiplyStringOutput(String string, int times) {
        for (int i = 0; i < times; i++){
            System.out.println(string);
        }
    }

    public static boolean isLeapYear (int year){
        return year%4==0;
    }

    public static void swapArrayValues() {
        int[] array = {1,1,1,0,0,0,1,0};
        for (int i = 0; i < array.length; i++){
            array[i] = (array[i] == 1)?0:1;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void fillArray() {
        int[] array = new int[100];
        for (int i = 0; i < array.length; i++){
            array[i] = i+1;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void alterArray() {
        int[] array = {1,5,3,2,11,4,5,2,4,8,9,1};
        for (int i = 0; i < array.length; i++){
            if (array[i] < 6) array[i]*=2;
        }
        System.out.println(Arrays.toString(array));
    }

    public static void alterArrayDiagonals() {
        int[][] array = {{4, 5, 6, 7},
                {8, 9, 5, 5},
                {9, 4, 5, 7},
                {8, 8, 8, 8}};

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    array[i][j] = 1;
                }
                if (i + j == array.length - 1) {
                    array[i][j] = 1;
                }
            }
        }
        System.out.println(Arrays.deepToString(array));
    }

    public static int[] createNumberArray(int len, int initialValue) {
        int[] array = new int[len];
        Arrays.fill(array, initialValue);
        System.out.println(Arrays.toString(array));
        return array;
    }
}
