import java.util.ArrayList;
import java.util.Scanner;

public class SumUsingAutoboxing {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numberList = new ArrayList<>();

        System.out.print("Enter numbers separated by spaces: ");
        String input = scanner.nextLine();

        String[] numbers = input.trim().split("\\s+");

        // Autoboxing: converting int to Integer automatically
        for (String numStr : numbers) {
            int num = Integer.parseInt(numStr); // parsing string to primitive int
            numberList.add(num);                // autoboxing to Integer
        }

        int sum = 0;
        // Unboxing: Integer to int during addition
        for (Integer number : numberList) {
            sum += number; // unboxing happens automatically here
        }

        System.out.println("Sum of numbers: " + sum);
        scanner.close();
    }
}
