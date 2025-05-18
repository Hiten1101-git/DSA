package InterviewQuestions.Amazon;

import java.util.*;
import java.util.stream.Collectors;

public class AmazonTransactionLogs {
    /**
     * Amazon Transaction Logs
     * Your Amazonian team is responsible for maintaining a monetary transaction service. The transactions are tracked in a log file.
     * A log file is provided as a string array where each entry represents a transaction to service. Each transaction consists of:
     * • sender_user_id: Unique identifier for the user that initiated the transaction. It consists of only digits with at most 9 digits.
     * • recipient_user_id: Unique identifier for the user that is receiving the transaction. It consists of only digits with at most 9 digits.
     * amount of transaction: The amount of the transaction. It consists of only digits with at most 9 digits.
     *.
     * The values are separated by a space. For example, "sender_user_id recipient_user_id amount_of_transaction".
     * Users that perform an excessive amount of transactions might be abusing the service so you have been tasked to identify the users that have a number of transactions over a threshold. The list of user ids should be ordered in ascending numeric value.
     *.
     * Example:
     * logs = ["88 99 200", "88 99 300", "99 32 100", "12 12 15"]
     * threshold = 2
     * Output: [88, 99]
     *.
     * Explanation:
     * The first transaction is from user 88 to user 99 with an amount of 200.
     * The second transaction is from user 88 to user 99 with an amount of 300.
     * The third transaction is from user 99 to user 32 with an amount of 100.
     * The fourth transaction is from user 12 to user 12 with an amount of 15.
     *.
     * Therefore, the users that have a number of transactions over the threshold of 2 are 88 and 99.
     */
    public static void main(String[] args) {
        List<String> logs = List.of("88 99 200", "88 99 300", "99 32 100", "12 12 15");
        int threshold = 2;
        System.out.println(processLogs(logs, threshold).toString());
    }

    /**
     * Function to process the logs and return the list of user ids that have a number of transactions over the threshold.
     * We can do this by using a hashmap to store the counts of each user.
     * We can then filter the users that have a count greater than or equal to the threshold.
     * Finally, we can sort the list of user ids and return it.
     * .
     * Time Complexity: O(n log n)
     * Space Complexity: O(n)
     *
     * @param logs - the list of logs
     * @param threshold - the threshold
     * @return - the list of user ids that have a number of transactions over the threshold
     */
    private static List<String> processLogs(List<String> logs, int threshold) {
        // Store counts for each user
        HashMap<Integer, Integer> hm = getUserTxnMap(logs);

        // Only keep users who meet the threshold
        List<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() >= threshold) {
                result.add(entry.getKey());
            }
        }

        // Sort and return as List of Strings
        Collections.sort(result);
        return result.stream().map(String::valueOf).collect(Collectors.toList());
    }

    private static HashMap<Integer, Integer> getUserTxnMap(final List<String> logs) {
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Process each log entry
        for (String log : logs) {
            String[] parts = log.split(" ");
            int sender = Integer.parseInt(parts[0]);
            int recipient = Integer.parseInt(parts[1]);

            // Only increment counts for valid sender/recipient pairs
            if (sender != recipient) {
                hm.put(sender, hm.getOrDefault(sender, 0) + 1);
                hm.put(recipient, hm.getOrDefault(recipient, 0) + 1);
            } else {
                hm.put(sender, hm.getOrDefault(sender, 0) + 1);
            }
        }
        return hm;
    }
}
