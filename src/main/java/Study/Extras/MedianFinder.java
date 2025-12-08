package Study.Extras;

import java.util.*;

public class MedianFinder {
    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        medianFinder.addNum(2);
        System.out.println("Median after adding 1 and 2: " + medianFinder.findMedian()); // Output: 1.5
        medianFinder.addNum(3);
        System.out.println("Median after adding 3: " + medianFinder.findMedian()); // Output: 2.0
        medianFinder.addNum(4);
        System.out.println("Median after adding 4: " + medianFinder.findMedian()); // Output: 2.5
    }

    private PriorityQueue<Integer> maxHeap; // left half (max-heap)
    private PriorityQueue<Integer> minHeap; // right half (min-heap)

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // Max-heap
        minHeap = new PriorityQueue<>(); // Min-heap
    }

    public void addNum(int num) {
        // Step 1: Add to maxHeap first
        maxHeap.offer(num);

        // Step 2: Balance by moving maxHeap root to minHeap
        minHeap.offer(maxHeap.poll());

        // Step 3: Ensure maxHeap has >= minHeap size
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }
}

