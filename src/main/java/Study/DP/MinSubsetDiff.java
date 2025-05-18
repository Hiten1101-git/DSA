package Study.DP;

import java.util.ArrayList;

public class MinSubsetDiff {
    public static void main(String[] args) {
        int[] array = {1, 6, 11, 5};
        int n = array.length;
        int range = 0;
        for(int i = 0 ; i < n ; i++) {
            range += array[i];
        }
        ArrayList<Integer> al = subsetSum(array, range, n);
        System.out.println(minDiff(range, al));
    }

    static ArrayList<Integer> subsetSum(int[] array, int range, int n) {
        ArrayList<Integer> al = new ArrayList<>();
        boolean[][] subsets = new boolean[n+1][range+1];

        for(int i = 0 ; i <= range ; i++) {
            subsets[0][i] = false;
        }
        for(int i = 0 ; i <= n ; i++) {
            subsets[i][0] = true;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j<= range ; j++) {
                subsets[i][j] = subsets[i-1][j];

                if(j >= array[i-1]) {
                    subsets[i][j] = subsets[i][j] || subsets[i-1][j-array[i-1]];
                }
            }
        }

        for(int i = 0 ; i <= range/2 ; i++) {
            if(subsets[n][i]) al.add(i);
        }
        return al;
    }

    static int minDiff(int range, ArrayList<Integer> al) {
        int mn = Integer.MAX_VALUE;
        for(int i = 0 ; i < al.size() ; i++) {
            mn = Math.min(mn, range - (2 * al.get(i)));
        }
        return mn;
    }
}
