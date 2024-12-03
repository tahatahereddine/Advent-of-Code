import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day3_Part1 {
    public static void main(String argv[]) {

        int sum = 0;
        File input = new File("input.txt");

        // Find mul(x,y) pattern
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            int character;
            while ((character = reader.read()) != -1) {
                char chr1 = (char) character;

                // find "mul"
                if (chr1 == 'm' && (char) reader.read() == 'u' && (char) reader.read() == 'l') {
                    if ((char) reader.read() == '(') {

                        int num1 = 0;
                        char digit = (char) reader.read();
                        while (Character.isDigit(digit)) {
                            num1 = num1 * 10 + (digit - '0');
                            digit = (char) reader.read();
                        }

                        if (digit == ',') {
                            int num2 = 0;
                            digit = (char) reader.read();
                            while (Character.isDigit(digit)) {
                                num2 = num2 * 10 + (digit - '0');
                                digit = (char) reader.read();
                            }

                            if (digit == ')') {
                                sum += num1 * num2;
                            }
                        }
                    }
                }
            }

            System.out.println("The sum of the multiplications is: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
