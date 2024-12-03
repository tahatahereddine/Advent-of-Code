import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class day2 {
    public static void main(String argv[]){
        List<List<Integer>> reports = new ArrayList<>();

        loadReports(reports);

        // Part 1
        int safeReports = 0;
        for(List<Integer> report : reports){
            if(safeReport(report))
                safeReports++;           
        }
        System.out.println("Number of safe reports: " + safeReports);


        // Part 2
        int safeReports2 = 0;
        
        for(List<Integer> report : reports){
            if(safeReport(report)){
                safeReports2++;
            }else if(safeAfterRemovingBadLevel(report)){
                safeReports2++;
            }
        }    
        System.out.println("Safe reports after considering Problem Dampener: " + safeReports2);       
    }


    public static void loadReports(List<List<Integer>> reports){
        File input = null;
        Scanner scanner = null;
        try{
            input = new File("input.txt");
            scanner = new Scanner(input);
        }catch(FileNotFoundException e){
            System.err.println(e);
        }
        // load the reports
        while(scanner.hasNextLine()){
            List<Integer> levels = new ArrayList<>();
            String numbers[] = scanner.nextLine().split("\\s+");

            for(int i=0; i<numbers.length; i++)
                levels.add(Integer.parseInt(numbers[i]));
            
            reports.add(levels);
        }
        scanner.close();
    }

    public static boolean safeReport(List<Integer> report){
        boolean safeRate = true, oneWayProgress = true;
        Integer progressDirection = null;
        int prev = report.get(0);

        for(int i=1; i<report.size(); i++){
            // check constant progress direction
            int diff = report.get(i) - prev;
            
            if(progressDirection != null){
                // check if the direction changed
                if(progressDirection != (diff > 0 ? 1 : -1) ){
                    oneWayProgress = false;
                    break;
                }
            }
            if(progressDirection == null){
                progressDirection = diff > 0 ? 1 : -1;
            }
            
            // check rate of change
            diff = Math.abs(diff);
            safeRate = diff >= 1 && diff <= 3;
            if(!safeRate)
                break;
            
            prev = report.get(i);
            
        } 

        return safeRate && oneWayProgress;
    }
    
    public static boolean safeAfterRemovingBadLevel(List<Integer> report){
        // remove levels and check if reports will still be safe
        for(int i=0; i<report.size(); i++){
            List<Integer> reportCopy = new ArrayList<>(report);
            reportCopy.remove(i);
            if(safeReport(reportCopy))
                return true;
        }
        return false;
    }
}