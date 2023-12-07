/** 
    _\/_
     /\          A program to figure out boat winning combinations
     /\          Solution to advent of code: Day 6/25
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

public class Main {
    public static void main(String[] args) {
        String[] input = Util.readFile(args[0]).split("\n");
        
        String[] t = input[0].split(":")[1].split(" ");
        String[] d = input[1].split(":")[1].split(" ");
        
        int[] times = new int[t.length];
        for(int i = 0; i < t.length; i++) {
            times[i] = Integer.parseInt(t[i]); 
        }
        
        int[] dist = new int[d.length];
        for(int i = 0; i < d.length; i++) {
            dist[i] = Integer.parseInt(d[i]);
        }

        long T = Util.concat(times);
        long D = Util.concat(dist);
        
        int ans = 1;
        for(int i = 0; i < times.length; i++) {
            ans *= f(times[i], dist[i]);
        }
        
        System.out.println(ans);
        System.out.println(f(T, D));
    }
    
    public static long f(long t, long d) {
        long lo = 0; 
        long hi = t/2;
        
        if(hi * (t - hi) < d) {
            return 0;
        }
        
        while(lo + 1 < hi) {
            long m = (lo + hi) / 2;
            if(g(m) >= d) {
                hi = m;
            } else {
                lo = m; 
            }
        }
        
        long first = hi;
        long last = (int)((t/2) + (t/2 - first));
        
        return (last - first + 1);
    }
    
    public static long g(long x) {
        return x * (t - x);
    }
    
    static class Util {
        public static String readFile(String file) {
            // read file implementation
        }
        
        public static long concat(int[] arr) {
            String s = "";
            for(int i : arr) {
                s += i;
            }
            return Long.parseLong(s);
        }
    }
}
