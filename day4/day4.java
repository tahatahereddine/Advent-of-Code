import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day4 {
    public static void main(String argv[]){
        List<String> words = new ArrayList<>();

        loadInput(words);

        int m = words.get(0).length() - 1; // len of columns
        int n = words.size() - 1; // len of lines

        // Part 1
        int xmasOccurence = 0;
        // check up/down/left/right
        // diagonal check (right-up/right-down)
        //                (left-up/left-down)
        for(int i=0; i<=n; i++){
            boolean upCheck = i >= 3;
            boolean downCheck = n-i >= 3;
            String word = words.get(i);
            for(int j=0; j<=m; j++){
                boolean rightCheck = m-j >= 3;
                boolean leftCheck = j >= 3;
                // System.out.print("i:"+i+",j:"+j+"\tm="+m+",n:"+n+"\n");
                if(word.charAt(j) == 'X'){ // Only check when the word starts with 'X'
                    char X = word.charAt(j);

                    if(upCheck){
                        String upWord = "" + X + words.get(i-1).charAt(j) + words.get(i-2).charAt(j) + words.get(i-3).charAt(j);
                        if(upWord.equals("XMAS"))
                            xmasOccurence++;
                    }
                    if(downCheck){
                        String downWord = "" + X + words.get(i+1).charAt(j) + words.get(i+2).charAt(j) + words.get(i+3).charAt(j);
                        if(downWord.equals("XMAS"))
                            xmasOccurence++;
                    }

                    if(rightCheck){
                        String rightWord = "" + X + word.charAt(j+1) + word.charAt(j+2) + word.charAt(j+3);
                        if(rightWord.equals("XMAS"))
                            xmasOccurence++;


                        // diagonalCheck
                        
                        if(upCheck){ // diag-up-right
                            String upRightWord = "" + X + words.get(i-1).charAt(j+1) + words.get(i-2).charAt(j+2) + words.get(i-3).charAt(j+3);
                            if(upRightWord.equals("XMAS"))
                                xmasOccurence++;
                        }
                        
                        if(downCheck){ // diag-down-right
                            String downRightWord = "" + X + words.get(i+1).charAt(j+1) + words.get(i+2).charAt(j+2) + words.get(i+3).charAt(j+3);
                            if(downRightWord.equals("XMAS"))
                                xmasOccurence++;
                        }
                    }
                    if(leftCheck){
                        String leftWord = "" + X + word.charAt(j-1) + word.charAt(j-2) + word.charAt(j-3);
                        if(leftWord.equals("XMAS"))
                            xmasOccurence++;

                        //diagonalCheck
                        if(upCheck){ // diag-up-left
                            String upLeftWord = "" + X + words.get(i-1).charAt(j-1) + words.get(i-2).charAt(j-2) + words.get(i-3).charAt(j-3);
                            if(upLeftWord.equals("XMAS"))
                                xmasOccurence++;
                        }
                        if(downCheck){ // diag-down-left
                            String downLeftWord = "" + X + words.get(i+1).charAt(j-1) + words.get(i+2).charAt(j-2) + words.get(i+3).charAt(j-3);
                            if(downLeftWord.equals("XMAS"))
                                xmasOccurence++;
                        }
                    }
                }
            }
        }
        
        System.out.println("XMAS occurences: " + xmasOccurence);

        // Part 2 (IN THE WORKS...)
        int xMasOccurence = 0;

        for(int i=0; i<=n; i++){
            boolean upCheck = i >= 2;
            boolean downCheck = n-i >= 2;
            String word = words.get(i);
            for(int j=0; j<=m; j++){
                boolean rightCheck = m-j >= 2;
                boolean leftCheck = j >= 2;
                if(word.charAt(j) == 'M'){ // Only check when the word starts with 'M'
                    char M = word.charAt(j);

                    // Check diagonal X-MAS
                    if(rightCheck && downCheck){
                        if(word.charAt(j+2) == 'M'){ // M . M
                                                     // . A .
                                                     // S . S
                            String MAS1 = "" + M + words.get(i+1).charAt(j+1) + words.get(i+2).charAt(j+2);
                            String MAS2 = "" + M + words.get(i+1).charAt(j+1) + words.get(i+2).charAt(j);
                            if(MAS1.equals(MAS2))
                                xMasOccurence++;

                        }else if(words.get(i+2).charAt(j) == 'M'){ // M . S
                                                                   // . A .
                                                                   // M . S
                            String MAS1 = "" + M + words.get(i+1).charAt(j+1) + words.get(i+2).charAt(j+2);
                            String MAS2 = "" + M + words.get(i-1).charAt(j+1) + words.get(i).charAt(j);
                            if(MAS1.equals(MAS2))
                                xMasOccurence++;
                        }             
                    
                    }
                }
            }
        }
        System.out.println("X-MAS occurences: " + xMasOccurence);

    }

    public static void loadInput(List<String> words){
        File input = null;
        Scanner scanner = null;
        try{
            input = new File("test.txt");
            scanner = new Scanner(input);
        } catch (FileNotFoundException e){
            System.err.println(e);
        }

        while(scanner.hasNextLine()){
            words.add(scanner.nextLine());
        }
        scanner.close();
    }
}