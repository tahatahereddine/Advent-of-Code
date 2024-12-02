import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) {
        List<Integer> locationsList1 = new ArrayList<Integer>();
        List<Integer> locationsList2 = new ArrayList<Integer>();

        loadNumbers(locationsList1, locationsList2);

        // Part 1
        int length = locationsList1.size();
        int i=0;
        Integer min1, min2;
        int sum = 0;
        for(;i<length;i++){
            // find mins
            min1 = Collections.min(locationsList1);
            min2 = Collections.min(locationsList2);

            // calculate difference
            sum += Math.abs(min2 - min1);

            // pop mins
            locationsList1.remove(min1);
            locationsList2.remove(min2);
        }
        System.out.println("Total distance between the lists: " + sum);

        // Part 2
        // load data again..
        loadNumbers(locationsList1, locationsList2);

        Map<Integer, Integer> locationsOccurences = new HashMap<>();
        int location;
        int sum2 = 0;

        // map the number of occurences of each location
        for(i=0; i<length; i++){
            location = locationsList1.get(i);
            if(!locationsOccurences.containsKey(location)){
                // find num of occurences of the location in the second list
                int occurences = 0, j=0;
                for(; j<length; j++)
                    if(location == locationsList2.get(j))
                        occurences++;
                locationsOccurences.put(location, occurences);
            }
        }

        // sum the total occurences
        for( Integer locID : locationsOccurences.keySet()){
            sum2 += locID * locationsOccurences.get(locID);
        }
        System.out.println("Similarity score: " + sum2);
    }

    public static void loadNumbers(List<Integer> l1, List<Integer> l2){
        File input = null;
        Scanner scanner = null;
        try{
            input = new File("input.txt");
            scanner = new Scanner(input);
        }catch(FileNotFoundException e){
            System.err.println(e);
        }
        // load the lists
        while(scanner.hasNextLine()){
            String numbers[] = scanner.nextLine().split("\\s+");
            l1.add(Integer.parseInt(numbers[0]));
            l2.add(Integer.parseInt(numbers[1]));
        }
        scanner.close();
    }
}