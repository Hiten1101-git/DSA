package Study.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * .
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * Explanation: The elements in spiral order are:
 *.
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
public class SpiralMatrix {
    public static void main(String[] args) {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.println(spiralMatrix.spiralOrder(matrix));
    }

    /**
     Maintain a top, bottom, left and right pointers and keep on updating
     the values for them
     Time: O(m * n)
     Space: O(1)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0, bottom = matrix.length - 1;
        int left = 0, right = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();

        // outer loop begins
        while (top <= bottom && left <= right) {
            // Top row
            for (int i = left ; i <= right ; i++) {
                result.add(matrix[top][i]);
            }
            top++;

            // right column
            for (int i = top ; i <= bottom ; i++) {
                result.add(matrix[i][right]);
            }
            right--;

            if (top <= bottom) {
                // bottom row
                for (int i = right ; i >= left ; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            if (left <= right) {
                // left column
                for (int i = bottom ; i >= top ; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }

        return result;
    }
}
