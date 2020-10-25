// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");
import java.util.ArrayList;
class Solution {
    public int solution(int[] A) {
        ArrayList<Integer> ints = new ArrayList<>();
        for(int i = 0; i <= 1000000 ; ++i){
            ints.add(new Integer(0));
        }
        for(int i = 0; i< A.length ; ++i){
            if(A[i] > 0)
                ints.set(A[i], A[i]);
        }
        for (int i = 0; i < ints.size(); i++) {
            if(ints.get(i) != i)
                return i;
        }
        return 0;
    }
}