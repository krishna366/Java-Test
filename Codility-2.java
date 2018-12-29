// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] T) {
        // write your code in Java SE 8
        
        int[] leftMax = new int[T.length];
        int[] rightMin = new int[T.length];
        
        int max = T[0], min = T[T.length - 1];
        
        for(int i=0;i<T.length;++i){
            if(max < T[i]) max = T[i];
            leftMax[i] = max;
        }
        
        for(int i=T.length-1;i>=0;--i){
            if(min > T[i]) min = T[i];
            rightMin[i] = min;
        }
        
        int c = 0;
        
        for(c = 0; c < T.length - 1;++c )
            if(leftMax[c] < rightMin[c+1]) break;
        
        return c+1;
    }
}
