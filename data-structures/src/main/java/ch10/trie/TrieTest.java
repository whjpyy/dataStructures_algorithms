package ch10.trie;

import ch07.set.BSTSet;
import ch07.set.FileOperation;

import java.util.ArrayList;

public class TrieTest {

    public static void main(String[] args) {
        ArrayList<String> words = new ArrayList<>();

        if(FileOperation.readFile("TheUnadjustedGirl_large.txt",words)){
            long startTime = System.nanoTime();

            BSTSet<String> set = new BSTSet<>();
            for (String word : words) {
                set.add(word);
            }
            for (String word : words) {
                set.contains(word);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("BSTSet: " + time + "s");


            startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }
            for (String word : words) {
                trie.contains(word);
            }
            endTime = System.nanoTime();
            time = (endTime - startTime) / 1000000000.0;
            System.out.println("Total different words: " + set.getSize());
            System.out.println("trie: " + time + "s");
        }
    }
}
