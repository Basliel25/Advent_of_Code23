/** 
    _\/_
     /\          A program to figure out the fertilizer optimization 
     /\          Solution to advent of code(23): Day 5/25
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

interface Transformer {
    int transformNumber(int x);
    List<int[]> transformRange(List<int[]> range);
}

class Pipeline {
    
    private List<Transformer> transformers;
    
    public Pipeline(List<Transformer> transformers) {
        this.transformers = transformers;
    }

    public int transformNumber(int x) {
        for(Transformer t : transformers) {
            x = t.transformNumber(x);  
        }
        return x;
    }
    
    public List<int[]> transformRange(List<int[]> range) {
        for (Transformer t : transformers) {
            range = t.transformRange(range);
        }
        return range;
    }
}

class FunctionAdapter implements Transformer {

    private Function function;
    
    public FunctionAdapter(Function function) {
        this.function = function;
    }

    public int transformNumber(int x) {
        return function.applyOne(x);
    }

    public List<int[]> transformRange(List<int[]> range){
        return function.applyRange(range); 
    }
}

public class FertilizerOptimize {

    public static void main(String[] args) {
        
        ArrayList<Transformer> transformers = new ArrayList<>();
        
        String input = args[0];
        
        // Build pipeline
        String[] parts = input.split("\n\n");
        for(int i=1; i < parts.length; i++) {
             Function function = new Function(parts[i]);
             transformers.add(new FunctionAdapter(function)); 
        }
        
        Pipeline pipeline = new Pipeline(transformers);
        
        // Part 1
        int[] seed = Arrays.stream(parts[0].split(":")[1].trim().split(" ")).mapToInt(Integer::parseInt).toArray();    
        List<Integer> p1 = new ArrayList<>();
        
        for(int x : seed) {
            p1.add(pipeline.transformNumber(x));    
        }
        
        // Part 2
        List<Integer> p2 = new ArrayList<>();
        for(int i=0; i< seed.length; i+=2) {
            int st = seed[i]; 
            int sz = seed[i+1];
            p2.add(pipeline.transformRange(Arrays.asList(new int[]{st, st+sz}))
                    .get(0)[0]);            
        }

        // output
        System.out.println(Collections.min(p1));  
        System.out.println(Collections.min(p2));
    }
}
