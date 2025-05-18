package Study.matrix;

import Study.Helper;

/**
 * <a href="https://leetcode.com/problems/rotate-image/description/?envType=study-plan-v2&envId=top-interview-150" />
 * You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
 *.
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
 * DO NOT allocate another 2D matrix and do the rotation.
 * .
 * Example:
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [[7,4,1],[8,5,2],[9,6,3]]
 * .
 * Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */
public class RotateMatrix {
    public static void main(String[] args) {
        Helper helper = new Helper();
        RotateMatrix rotateMatrix = new RotateMatrix();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        helper.printMatrix(matrix, "Input Matrix: ");
        rotateMatrix.approach2(matrix);
        helper.printMatrix(matrix, "Rotated Matrix: ");
    }

    private void approach2(int[][] mat) {
        transpose(mat);
        reverseByRow(mat);
    }

    private void transpose(int[][] mat) {
        for (int i = 0 ; i < mat.length ; i++) {
            for (int j = i + 1 ; j < mat[0].length ; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }

    private void reverseByRow(int[][] mat) {
        for (int i = 0 ; i < mat.length ; i++) {
            int left = 0, right = mat[0].length - 1;
            while (left < right) {
                int temp = mat[i][left];
                mat[i][left] = mat[i][right];
                mat[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    /**
     - i < n/2, Only process outer half of layers
     - j = i to n-1-i, Only process top elements of the layer (others handled in swaps)

     Time: O(n2)
     Space: O(1)
     */
    private void approach1(int[][] mat) {
        int n = mat.length;
        for (int i = 0 ; i < n / 2 ; i++) {
            for (int j = i ; j < n - 1 - i ; j++) {
                int a = i, b = j;
                int reversedVal = mat[a][b];
                do {
                    int temp = mat[b][n - 1 - a];
                    mat[b][n - 1 - a] = reversedVal;
                    reversedVal = temp;

                    temp = b;
                    b = n - 1 - a;
                    a = temp;
                } while (a != i || b != j);
            }
        }
    }
}
