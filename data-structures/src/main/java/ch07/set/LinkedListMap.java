package ch07.set;

import java.util.ArrayList;
import java.util.List;

public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null,null);
        }

        @Override
        public String toString() {
            return key.toString() + ": " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    /**
     * 通过key获取到对应的节点
     * @param key
     * @return
     */
    private Node getNode(K key){
        Node cur = dummyHead.next;
        while (cur != null){
            if(cur.key.equals(key)){
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }else{ // 修改对应的值
            node.value = value;
        }

    }

    @Override
    public V remove(K key) {
        Node prev = this.dummyHead;
        while (prev.next != null){
            if(prev.next.key.equals(key)){
                break;
            }
            prev = prev.next;
        }
        if(prev.next != null){
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        node.value = value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        if(FileOperation.readFile("TheUnadjustedGirl.txt",words)){
            System.out.println("Total words: " + words.size());

            Map<String, Integer> linkedListMap = new LinkedListMap<>();
            for (String word : words) {
                if(linkedListMap.contains(word)){
                    linkedListMap.set(word, linkedListMap.get(word) + 1);
                }else{
                    linkedListMap.add(word, 1);
                }
            }
            System.out.println("Total different words: " + linkedListMap.getSize());
            System.out.println("Frequency of HUMAN: " + linkedListMap.get("human"));
        }
    }
}
