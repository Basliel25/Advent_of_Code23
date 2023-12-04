/** 
    _\/_
     /\          A program to figure out scratchcards 
     /\          Solution to advent of code(23): Day 4/25
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

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        
        // Read input file contents into a string 
        String input = new String(Files.readAllBytes(Paths.get(args[0])));
        
        // Split into lines
        String[] lines = input.split("\n");
        
        int p1 = 0;
        
        // Use HashMap instead of defaultdict
        Map<Integer, Integer> N = new HashMap<>();
        
        for (int i = 0; i < lines.length; i++) {
            
            // Initialize counter for this line 
            N.put(i, 0); 
            
            String[] parts = lines[i].split("\\|");
            
            // First part has card, second has rest
            String cardStr = parts[0].split(":")[1]; 
            String restStr = parts[1];
            
            // Convert strings to integer arrays
            int[] cardNums = Arrays.stream(cardStr.split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] restNums = Arrays.stream(restStr.split(" ")).mapToInt(Integer::parseInt).toArray();
            
            // Count intersection
            int val = (int) Arrays.stream(cardNums).filter(x -> Arrays.stream(restNums).anyMatch(y -> y == x)).count();
            
            // Update p1
            if (val > 0) {
                p1 += (int) Math.pow(2, val - 1); 
            }
            
            // Update N
            for (int j = 0; j < val; j++) {
                N.put(i + j + 1, N.getOrDefault(i + j + 1, 0) + N.get(i));
            }
        }

        System.out.println(p1);
        
        // Sum values
        int sum = N.values().stream().mapToInt(x -> x).sum(); 
        System.out.println(sum);
    }

}
