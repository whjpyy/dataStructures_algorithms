package ch13.redblacktree;

import ch06.bst.BST;
import ch07.set.BSTMap;
import ch12.avltree.AVLTree;

import java.util.ArrayList;
import java.util.Random;

public class Test2 {

    public static void main(String[] args) {
        int n = 20000000;

        Random random = new Random();
        ArrayList<Integer> testData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            testData.add(random.nextInt(Integer.MAX_VALUE));
        }

        // TEST_BST
        long startTime = System.nanoTime();
        BSTMap<Integer, Integer> bst = new BSTMap<>();
        for (Integer x : testData) {
            bst.add(x, null);
        }
        long endTime = System.nanoTime();
        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("BST: " + time);

        // TEST_AVL
        startTime = System.nanoTime();
        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (Integer x : testData) {
            avlTree.add(x, null);
        }
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("AVLTree: " + time);

        // TEST_RBT
        startTime = System.nanoTime();
        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer x : testData) {
            rbTree.add(x, null);
        }
        endTime = System.nanoTime();
        time = (endTime - startTime) / 1000000000.0;
        System.out.println("RBTree: " + time);

    }
}
