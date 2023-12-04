/** 
    _\/_
     /\          A program to figure out gear ratios
     /\          Solution to advent of code: Day 3/25
    /  \
    /~~\o        @author Basliel B. Gugsa
   /o   \
  /~~*~~~\
 o/    o \
 /~~~~~~~~\~`
/__*_______\
     ||
   \====/
    \__/
/ * * * * *  
*/



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {

    public static void main(String[] args) throws FileNotFoundException {
        
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        
        int part1 = part1(scanner);
        System.out.println(part1);
        
        scanner.reset();
        int part2 = part2(scanner); 
        System.out.println(part2);

        scanner.close();
    }

    public static int part(Scanner scanner) {
        List<String> lines = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }
        
        int total = 0;
        
        Pattern pattern = Pattern.compile("\\d+");
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int num = Integer.parseInt(matcher.group());
                
                int start = matcher.start() - 1;
                int end = matcher.end();
                
                total += checkAdjacent(lines, i-1, start, end, num) ? num : 0;
                total += checkAdjacent(lines, i+1, start, end, num) ? num : 0;               
            }
        }
        
        return total;
    }
    
    public static boolean checkAdjacent(List<String> lines, int i, int start, int end, int num) {
        if (i < 0 || i >= lines.size()) {
            return false; 
        }
        
        String line = lines.get(i);
        
        for (int j = start; j < end; j++) {
            if (line.charAt(j) == '*') {
                return true;
            }
        }
        
        return false;
    }

    public static int part2(Scanner scanner) {
        List<String> lines = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine()); 
        }
       
        Map<String, List<Integer>> map = new HashMap<>();
        
        Pattern pattern = Pattern.compile("\\d+");
        
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                int num = Integer.parseInt(matcher.group());
                
                int start = matcher.start() - 1; 
                int end = matcher.end();
                
                checkAndAdd(lines, map, i-1, start, end, num);
                checkAndAdd(lines, map, i+1, start, end, num);
            }
        }

        int total = 0;
        
        for (List<Integer> list : map.values()) {
            if (list.size() == 2) {
                total += list.get(0) * list.get(1); 
            }
        }
        
        return total;
    }
    
    public static void checkAndAdd(List<String> lines, Map<String, List<Integer>> map, int i, int start, int end, int num) {
        if (i < 0 || i >= lines.size()) {
            return;
        }
        
        String line = lines.get(i);
        
        for (int j = start; j < end; j++) {
            if (line.charAt(j) == '*') {
                String key = i + "," + j;
                
                if (!map.containsKey(key)) {
                    map.put(key, new ArrayList<>());
                }
                
                map.get(key).add(num);
                return;
            }
        }
    }
}
