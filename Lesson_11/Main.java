import java.util.*;

public class Main {
    public static void main(String[] args) {
        //task #1
        List<String> words = new ArrayList<>(Arrays.asList("яблоко", "груша", "яблоко", "апельсин", "груша", "банан", "яблоко", "груша", "персик", "апельсин"));
        Map<String, Integer> wordCount = new HashMap<>();

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println("Уникальные слова и их количество в массиве:");
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " раз(а)");
        }

        //task #2
        PhoneDirectory pd = new PhoneDirectory();
        pd.addEntry("Иванов", "89120001122");
        pd.addEntry("Иванов", "891211112233");
        pd.addEntry("Петров", "891233334455");
        pd.addEntry("Сергеев", "891244445566");

        pd.getEntry("Иванов");
    }
}