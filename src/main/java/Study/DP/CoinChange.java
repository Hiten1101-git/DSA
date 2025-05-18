package Study.DP;

public class CoinChange {
    public static void main(String[] args) {
        int[] coin = {1,2,3};
        int sum = 5;
        int n = coin.length;
        System.out.println(CoinChange(coin, sum, n));
    }

    static int CoinChange(int[] coin, int sum, int n) {
        int subsets[][] = new int[n+1][sum+1];

        for(int i = 0 ; i <= sum ; i++) {
            subsets[0][i] = 0;
        }
        for(int i = 0 ; i <= n ; i++) {
            subsets[i][0] = 1;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j<= sum ; j++) {
                subsets[i][j] = subsets[i-1][j];

                if(j >= coin[i-1]) {
                    subsets[i][j] = subsets[i-1][j] + subsets[i][j-coin[i-1]];
                } else {
                    subsets[i][j] = subsets[i-1][j];
                }
            }
        }

        return subsets[n][sum];
    }
}
