package ch07.set;

import java.util.ArrayList;
import java.util.List;

public class SetTest {

    private static double testSet(Set<String> set, List<String> words){
        long startTIme = System.nanoTime();
        System.out.println("Total words: " + words.size());
        for (String word : words) {
            set.add(word);
        }
        System.out.println("Total differrent words: " + set.getSize());
        return (System.nanoTime() - startTIme) / 1000000000.0;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        FileOperation.readFile("TheUnadjustedGirl.txt", words);

        Set<String> bstSet = new BSTSet<>();
        double time1 = testSet(bstSet, words);
        System.out.println("BST set: " + time1 + "s");
        System.out.println();
        Set<String> linkedListSet = new LinkedListSet<>();
        double time2 = testSet(linkedListSet, words);
        System.out.println("LinkedListSet set: " + time2 + "s");

    }
}
