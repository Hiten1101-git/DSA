package InterviewQuestions.Amazon;

import java.util.Arrays;
import java.util.List;

public class AmazonRewardsTournament {
    /**
     * Amazon shopping is running a reward collection event for it’s customers there are n customers and ith customer has collected initialRewards[i] points so far
     * One final tournament is to take place whee the winner will be awarded n points, the runner up will be given n-1, third place n -2 and so on the last person would receive 1 point
     * Given an integer array initalRewards on length n, representing the initial rewards find the number of customers such that if the ith customer wins the final, they would have highest points.
     * N=3
     * [1,3,4]
     * If the 1st customer wins the final tournament their total points would be 1+3=4
     * But if 3rs customer comes 2nd in finals his points would be 4+2=6 which is greater than the 1st hence this is not the ans
     *.
     * If the 2nd  customer wins the final tournament their total points would be 3+3=6
     * Even if 3rs customer comes 2nd in finals his points would be 4+2=6 which is equal to 2nd hence this is considered as ans
     *.
     * If the 3rd customer wins the final tournament their total points would be 4+3=7
     * Which is the highest as we understand
     *.
     * Hence, final answer is 2
     */
    public static void main(String[] args) {
        List<Integer> initialRewards = Arrays.asList(1, 3, 4);
        System.out.println(findPossibleWinners(initialRewards)); // Output: 2
    }

    /**
     * Function to find the number of customers that can win the tournament
     * We can do this by finding the maximum and second maximum initial rewards.
     * We can then check if the customer can win the tournament by checking if their score is greater than or equal to the maximum score of their competitors.
     * If it is, we can increment the count.
     *.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param initialRewards - the list of initial rewards
     * @return - the number of customers that can win the tournament
     */
    public static int findPossibleWinners(List<Integer> initialRewards) {
        int n = initialRewards.size();

        // Step 1: Find the maximum and second maximum initial rewards
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;
        for (int reward : initialRewards) {
            if (reward > max1) {
                max2 = max1;   // previous max becomes second max
                max1 = reward; // update new max
            } else if (reward > max2) {
                max2 = reward; // update second max if necessary
            }
        }

        int count = 0;

        // Step 2: Check each customer
        for (int reward : initialRewards) {
            int winnerScore = reward + n;  // Winning adds 'n' points
            int competitorMaxScore = reward == max1 ? max2 + (n - 1) : max1 + (n - 1); //
            if (winnerScore >= competitorMaxScore) {
                count++;
            }
        }

        return count;
    }
}
