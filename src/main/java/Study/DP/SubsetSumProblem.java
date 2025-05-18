package Study.DP;

// MEMOIZATION

public class SubsetSumProblem {
    static int[] array = { 3, 34, 4, 12, 5, 2 };
    static int sum = 9;
    static int n = array.length;

    public static void main(String[] args) {
        System.out.println(subsetSumProblem(array, sum, n));
    }


    static boolean subsetSumProblem(int[] array, int sum, int n) {

        boolean subset[][] = new boolean[sum + 1][n + 1];

//        matrix initialised with
//        1 0 0 0 0 0
//        1
//        1
//        1
//        1
        for(int j = 0 ; j <= n ; j++) {
            subset[0][j] = true;
        }
        for(int i = 1 ; i <= sum ; i++) {
            subset[i][0] = false;
        }

        // checking for each entry of matrix
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
