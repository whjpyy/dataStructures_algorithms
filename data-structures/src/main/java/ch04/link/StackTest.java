package ch04.link;

import ch03.stack.ArrayStack;
import ch03.stack.Stack;

import java.util.Random;

/**
 * @author carl.z.chen
 * @Date 2019/9/26
 */
public class StackTest {

    private static double testStack(Stack<Integer> q, int optCount){

        long start1 = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < optCount; i++) {
            q.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < optCount; i++) {
            q.pop();
        }
        long start2 = System.nanoTime();
        return (start2 - start1) / 1000000000.0;
    }

    public static void main(String[] args) {

        int optCount = 1000000;
        Stack<Integer> arrayStack = new ArrayStack<>();
        double time1 = testStack(arrayStack, optCount);
        System.out.println("arrayStack time:" + time1);

        Stack<Integer> linkedListStack = new LinkedListStack<>();
        double time2 = testStack(linkedListStack, optCount);
        System.out.println("LinkedListStack time:" + time2);
    }
}
