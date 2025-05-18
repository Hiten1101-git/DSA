package Study.Intervals;

import java.util.Arrays;

public class BurstingBalloons {
    public static void main(String[] args) {
        int[][] points = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        BurstingBalloons bb = new BurstingBalloons();
        System.out.println(bb.findMinArrowShots(points));
    }

    public int findMinArrowShots(int[][] points) {
        return approach2(points);
    }

    /**
     Time complexity: O(n*log(n))
     Space complexity: O(1)
     */
    private int approach2(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));

        int arrowCounter = 1;
        int currentEnd = points[0][1];

        for (int i = 1 ; i < points.length ; i++) {
            if (points[i][0] <= currentEnd) {
                currentEnd = Math.min(currentEnd, points[i][1]);
            } else {
                arrowCounter++;
                currentEnd = points[i][1];
            }
        }

        return arrowCounter;
    }

    /**
     - First, we sort the balloons based on their end coordinates. This allows
     us to iterate through the balloons in ascending order of their end coordinates.
     - We initialize the variable arrows to 1, assuming that at least one arrow
     is needed.
     - We iterate through the sorted balloons and compare the start coordinate
     of the current balloon with the end coordinate of the previous balloon.
     - If the start coordinate of the current balloon is greater than the end
     coordinate of the previous balloon, it means these two balloons do not
     overlap, so we need to shoot another arrow. We increment the arrows count
     and update the prevEnd variable to the end coordinate of the current balloon.
     - After iterating through all balloons, arrows will contain the minimum number
     of arrows needed.

     Time complexity: O(n*log(n))
     Space complexity: O(1)
     */
    public int approach1(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrowCounter = 1;
        int prevEnd = points[0][1];

        for (int i = 1 ; i < points.length ; i++) {
            if (points[i][0] > prevEnd) {
                arrowCounter++;
                prevEnd = points[i][1];
            }
        }

        return arrowCounter;
    }
}
