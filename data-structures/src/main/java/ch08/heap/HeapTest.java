package ch08.heap;

import java.util.Random;

public class HeapTest {

    private static double testHeap(Integer[] testData, boolean isHeapify){
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if(isHeapify){
            maxHeap = new MaxHeap<>(testData);
        }else {
            maxHeap = new MaxHeap<>();
            for (Integer num : testData) {
                maxHeap.add(num);
            }
        }

        // 验证是否正确
        int n = testData.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        for (int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap complete.");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        Random random = new Random();
        int n = 5000000;
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }

        double time1 = testHeap(testData, false);
        System.out.println("Without heapify: " + time1);

        double time2 = testHeap(testData, true);
        System.out.println("heapify: " + time2);
    }
}
