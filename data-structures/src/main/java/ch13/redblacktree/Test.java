package ch13.redblacktree;

import ch07.set.BSTMap;
import ch07.set.FileOperation;
import ch07.set.Map;
import ch12.avltree.AVLTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        if(FileOperation.readFile("TheUnadjustedGirl.txt",words)){
            Collections.sort(words);

            long startTime = System.nanoTime();

            Map<String, Integer> bst = new BSTMap<>();
            for (String word : words) {
                if(bst.contains(word)){
                    bst.set(word, bst.get(word) + 1);
                }else{
                    bst.add(word, 1);
                }
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BST: " + time + "s");

            startTime = System.nanoTime();
            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if(avlTree.contains(word)){
                    avlTree.set(word, avlTree.get(word) + 1);
                }else{
                    avlTree.add(word, 1);
                }
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + "s");


            startTime = System.nanoTime();
            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if(rbTree.contains(word)){
                    rbTree.set(word, rbTree.get(word) + 1);
                }else{
                    rbTree.add(word, 1);
                }
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time + "s");
        }
    }
}
