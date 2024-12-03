import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Day3_Part2 {
    public static void main(String argv[]) {

        int sum = 0;
        File input = new File("input.txt");

        // Find mul(x,y) pattern
        try (BufferedReader reader = new BufferedReader(new FileReader(input))) {
            int character;
            boolean doFlag = true;
            while ((character = reader.read()) != -1) {
                char chr = (char) character;
                
                // check future mul instruction (do / don't)
                if(chr == 'd' && (char) reader.read() == 'o'){
                    chr = (char) reader.read();
                    // do()
                    if(chr == '(' && (char) reader.read() == ')'){
                        doFlag = true;
                    } // don't()
                    if(chr == 'n' && (char) reader.read() == '\'' && (char) reader.read() == 't' 
                    && (char) reader.read() == '(' && (char) reader.read() == ')') {
                        doFlag = false;
                    }
                }

                // find mul(x,y) pattern 
                // if mul instruction is set to do()
                if(doFlag){
                    if (chr == 'm' && (char) reader.read() == 'u' && (char) reader.read() == 'l') {
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
            }
            
            System.out.println("The NEW sum of the multiplications is: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}