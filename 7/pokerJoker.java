/** 
    _\/_
     /\          A program to figure out boat winning combinations
     /\          Solution to advent of code: Day 7/25
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

public class Solution {

    /**
     * Calculates strength of a hand of cards
     * @param hand The hand of cards
     * @param part2 Whether this is part 2 of problem
     * @return Tuple of strength value and hand  
     */
    public static Tuple strength(String hand, boolean part2) {
        // Replace face cards with values
        hand = hand.replace("T", String.valueOf((char)(ord('9') + 1))); 
        if(!part2) {
            hand = hand.replace("J", String.valueOf((char)(ord('9') + 2)));
        } else {
            hand = hand.replace("J", String.valueOf((char)(ord('2') - 1)));  
        }
        hand = hand.replace("Q", String.valueOf((char)(ord('9') + 3)));
        hand = hand.replace("K", String.valueOf((char)(ord('9') + 4))); 
        hand = hand.replace("A", String.valueOf((char)(ord('9') + 5)));
        
        Counter<String> C = new Counter<>(); 
        C.addAll(Arrays.asList(hand.split("")));
        
        // Rest of logic to calculate strength
        
        return new Tuple(strengthValue, hand); 
    }

    /** 
     * Main method 
     */
    public static void main(String[] args) {
        
        List<String> input = Files.readAllLines(Paths.get(args[0]));
        
        List<Tuple> H = new ArrayList();
        
        for(String line : input) {
           // Parse input line 
           H.add(new Tuple(hand, bid)); 
        }

        // Sort hands 
        Collections.sort(H, (a, b) -> {
             return strength(a.hand, false).strength - strength(b.hand, false).strength; 
        });
        
        // Calculate score
        int ans = 0;
        for(int i = 0; i < H.size(); i++) {
            Tuple t = H.get(i);
            ans += (i+1) * Integer.parseInt(t.bid);
        }
        
        System.out.println(ans);
    }

    static class Tuple {
        String hand; 
        String bid;
        
        // Constructor and methods 
    }

}
