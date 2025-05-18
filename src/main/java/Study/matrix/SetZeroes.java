package Study.matrix;

import Study.Helper;

import java.util.Arrays;

public class SetZeroes {
    public static void main(String[] args) {
        Helper helper = new Helper();
        SetZeroes setZeroes = new SetZeroes();
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        helper.printMatrix(matrix, "Input Matrix: ");
        setZeroes.setZeroes(matrix);
        helper.printMatrix(matrix, "Output Matrix: ");
    }

    public void setZeroes(int[][] matrix) {
        optimalSolution(matrix);
    }

    /**
     first we will traverse the 0th row and 0th column of the given matrix and
     if we encounter any 0 then we will set the isRow0/isCol0 variable to true
     which indicates that the 0th row/0th column of the given matrix will become 0
     next we will traverse the remaining matrix except 0th row and 0th column and
     if we encounter any 0, we will make the corresponding row no. and column no.
     equal to 0 in the 0th column and 0th row respectively
     Now we will update the values of the matrix except first row and first column
     to 0 if matrix[i][0]=0 or matrix[0][j]=0 for any (i,j).
     finally we will traverse the 0th row and 0th column and if we find any 0,
     we will make the whole row and whole column equal to 0

     Time: O(mn), Space: O(1)
     */
    private void optimalSolution(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean isRow0 = false, isCol0 = false;

        for (int j = 0 ; j < n ; j++) {
            if (matrix[0][j] == 0) isRow0 = true;
        }

        for (int i = 0 ; i < m ; i++) {
            if (matrix[i][0] == 0) isCol0 = true;
        }

        for (int i = 1 ; i < m ; i++) {
            for (int j = 1 ; j < n ; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1 ; i < m ; i++) {
            for (int j = 1 ; j < n ; j++) {
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        if (isRow0) {
            for (int j = 0 ; j < n ; j++) {
                matrix[0][j] = 0;
            }
        }

        if (isCol0) {
            for (int i = 0 ; i < m ; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    /**
     we can use two separate arrays one for rows (rowsArray) and one for columns
     (colsArray) and initialize them to 1
     while traversing the given matrix whenever we encounter 0 at (i,j),
     we will set rowsArray[i]=0 and colsArray[j]=0
     After completion of step 2, again iterate through the matrix and
     for any (i,j), if rowsArray[i] or colsArray[j] is 0 then update matrix[i][j] to 0.

     Time: O(mn), Space: O(m + n)
     */
    private void bruteForceOptimal(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rowArr = new int[m];
        int[] colArr = new int[n];

        Arrays.fill(rowArr, 1);
        Arrays.fill(colArr, 1);

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (matrix[i][j] == 0) {
                    rowArr[i] = 0;
                    colArr[j] = 0;
                }
            }
        }

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (rowArr[i] == 0 || colArr[j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     we can copy all the elements of given matrix to matrix2
     while traversing given matrix whenever we encounter 0, we will make
     the entire row and column of the matrix2 to 0
     finally we can again copy all the elements of matrix2 to given matrix

     Time: O((mn)∗(m+n)), Space: O(mn)
     */
    private void bruteForce(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] matrix2 = new int[m][n];

        // copy all elements to new matrix
        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                matrix2[i][j] = matrix[i][j];
            }
        }

        // if any element is 0, mark all elements of that row n column as 0
        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0 ; k < n ; k++) {
                        matrix2[i][k] = 0;
                    }
                    for (int k = 0 ; k < m ; k++) {
                        matrix2[k][j] = 0;
                    }
                }
            }
        }

        for (int i = 0 ; i < m ; i++) {
            for (int j = 0 ; j < n ; j++) {
                matrix[i][j] = matrix2[i][j];
            }
        }
    }
}
