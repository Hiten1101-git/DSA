package Study.DP;

// MEMOIZATION

public class Knapsack01 {
    static int[] wt = {10, 5, 10, 10};
    static int[] val = {10, 2, 1, 3};
    static int w = 20;
    static int n = wt.length;
    static int t[][] = new int[n+1][w+1];


    public static void main(String[] args) {
        fill(n,w);
        System.out.println(knapsack(wt, val, w, n));
    }

    // to initialize the matrix with -1
    static void fill(int n, int w) {
        for(int i = 0 ; i <= n ; i++) {
            for(int j = 0 ; j <= w ; j++) {
                t[i][j] = -1;
            }
        }
    }

    static int knapsack(int wt[], int val[], int w, int n) {

        // if number of elements or weight required is 0, return nothing
        if( n == 0 || w == 0) return 0;

        // if value is present in the matrix, return the value
        if(t[n][w] != -1) return t[n][w];

        // choice diagram
        if(wt[n-1] <= w) {
            return t[n][w] = Math.max(
                    val[n-1] + knapsack(wt, val, w-wt[n-1], n-1) ,
                    knapsack(wt, val, w, n-1)
            );
        }
        else if (wt[n-1] > w) {
            return t[n][w] = knapsack(wt, val, w, n - 1);
        }
        return -1;
    }
}
