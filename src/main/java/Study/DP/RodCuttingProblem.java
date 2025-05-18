package Study.DP;

public class RodCuttingProblem {
    public static void main(String[] args) {
        int n = 8;
        int length[] = new int[n];
        int price[] = {1,5,8,9,10,17,17,20};

        for(int i = 0 ; i < n ; i++) {
            length[i] = i + 1;
        }

        System.out.println(rodCuttingProblem(length, price, n));
    }

    static int rodCuttingProblem(int[] length, int[] price, int n) {
        int subsets[][] = new int[n+1][n+1];

        for(int i = 0 ; i <= n ; i++) {
            subsets[0][i] = 0;
        }
        for(int i = 0 ; i <= n ; i++) {
            subsets[i][0] = 1;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j<= n ; j++) {
                subsets[i][j] = subsets[i-1][j];

                if(j >= length[i-1]) {
                    subsets[i][j] = Math.max(price[i-1] + subsets[i][j-length[i-1]] , subsets[i-1][j] );
                } else {
                    subsets[i][j] = subsets[i-1][j];
                }
            }
        }

        return subsets[n][n];
    }
}
