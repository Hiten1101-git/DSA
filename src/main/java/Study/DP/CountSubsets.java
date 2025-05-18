package Study.DP;

public class CountSubsets {
    public static void main(String[] args) {
        int array[] = {2,3,5,6,8,10};
        int sum = 10;
        int n = array.length;

        System.out.println(CountSubsets(array, sum, n));
    }

    static int CountSubsets(int[] array, int sum, int n) {
        int[][] subsets = new int[n+1][sum+1];

        for(int i = 0 ; i <= sum ; i++) {
            subsets[0][i] = 0;
        }
        for(int i = 0 ; i <= n ; i++) {
            subsets[i][0] = 1;
        }

        for(int i = 1 ; i <= n ; i++) {
            for(int j = 1 ; j <= sum ; j++) {
                subsets[i][j] = subsets[i-1][j];

                if(j >= array[i-1]) {
                    subsets[i][j] = subsets[i][j] + subsets[i-1][j-array[i-1]];
                }
            }
        }

        return subsets[n][sum];
    }
}
