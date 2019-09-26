package ch03.queue;

import java.util.Random;

/**
 * @author carl.z.chen
 * @Date 2019/9/26
 */
public class QueueTest {

    private static double testQueue(Queue<Integer> q, int optCount){

        long start1 = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            q.enquque(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            q.dequeue();
        }
        long start2 = System.nanoTime();
        return (start2 - start1) / 1000000000.0;
    }

    public static void main(String[] args) {

        int optCount = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, optCount);
        System.out.println("ArrayQueue time:" + time1);

        Queue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, optCount);
        System.out.println("LoopQueue time:" + time2);
    }
}
