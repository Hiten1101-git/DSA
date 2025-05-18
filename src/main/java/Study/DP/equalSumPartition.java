package Study.DP;

public class equalSumPartition {
    static int array[] = {1, 5, 11, 5};
//    static int sum = 22;
    static int n = array.length;
    public static void main(String[] args) {
        System.out.println(equalSumPartition(array, n));
    }

    static boolean equalSumPartition(int array[], int n) {
        int sum = 0;
        for(int i = 0 ; i < n ; i++) {
            sum += array[i];
        }

        if(sum%2 != 0) return false;
        return subsetSum(array,sum%2, n);
    }

    static boolean subsetSum(int[] array, int sum, int n) {
        boolean subset[][] = new boolean[sum + 1][n + 1];

        for(int j = 0 ; j <= n ; j++) {
            subset[sum][n] = true;
        }
        for(int i = 1 ; i <= sum ; i++) {
            subset[sum][n] = false;
        }

        for(int i = 1 ; i <= sum ; i++) {
            for(int j = 1 ; j <= n ; j++) {
                subset[i][j] = subset[i][j-1];

                if(i >= array[j-1]) {
                    subset[i][j] = subset[i][j] || subset[i-array[j-1]][j-1];
                }
            }
        }

        return subset[sum][n];
    }
}
