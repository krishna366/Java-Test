// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int[] A, int K, int L) {
        // write your code in Java SE 8
        int count = 0;
        int maxIndex = 0;
        
        if(A.length < K + L) return -1;
  
        if(K < L){
            int t=L;
            L=K;
            K=t;
        }
        
        //K is always greater than L now.
    
        for(int i=0;i<K;++i)
            count+= A[i];
            
        int runningTotal = count;
        maxIndex = K - 1;
        for(int i=K;i<A.length;++i){
            runningTotal += (A[i] - A[i-K]);
            
            if(runningTotal > count){
                count = runningTotal;
                maxIndex = i;
            }
            
        }
        
        //Now we have max sum with K at a time.
        //Now pick L at a time without touching maxIndex - K to maxIndex
        
        int count2 = 0;
        
        int lStartIndex = maxIndex - K < L ? maxIndex + 1 : 0;
        
        for(int i=lStartIndex;i<lStartIndex+L;++i)
            count2+= A[i];
        
        runningTotal = count2;
        
        for(int i=lStartIndex+L;i<A.length;++i){
            
            runningTotal += (A[i] - A[i-L]);
            
            if(i < maxIndex - K || i > maxIndex ){
                
                if(runningTotal > count2) count2 = runningTotal;
                
            }
            
        }
        
        return count + count2;    
    }
    
    int getSum(int[] A,int s,int l){
        int c = 0;
        
        for(int i=s;i<=l;++i) c+=A[i];
        
        return c;
    }
}
