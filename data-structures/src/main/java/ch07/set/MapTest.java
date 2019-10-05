package ch07.set;

import java.util.ArrayList;
import java.util.List;

public class MapTest {

    private static double testMap(Map<String, Integer> map, List<String> words){
        long startTIme = System.nanoTime();
        System.out.println("Total words: " + words.size());
        for (String word : words) {
            if(map.contains(word)){
                map.set(word, map.get(word) + 1);
            }else{
                map.add(word, 1);
            }
        }
        System.out.println("Total differrent words: " + map.getSize());
        System.out.println("Frequency of HUMAN: " + map.get("human"));
        System.out.println("Frequency of GIRL: " + map.get("girl"));
        return (System.nanoTime() - startTIme) / 1000000000.0;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        FileOperation.readFile("TheUnadjustedGirl.txt", words);

        Map<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(bstMap, words);
        System.out.println("BSTMap map: " + time1 + "s");
        System.out.println();
        Map<String, Integer> linkedListMap = new LinkedListMap<>();
        double time2 = testMap(linkedListMap, words);
        System.out.println("LinkedListMap map: " + time2 + "s");

    }
}
