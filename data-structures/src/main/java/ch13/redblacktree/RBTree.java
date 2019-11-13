package ch13.redblacktree;

import ch07.set.FileOperation;
import ch07.set.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * 红黑树
 * @param <K>
 * @param <V>
 */
public class RBTree<K extends Comparable<K>, V> implements Map<K, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }
    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }


    /**
     * 向红黑树中添加新的元素(key, value)
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
        // 最终根节点为黑色节点
        root.color = BLACK;
    }

    /**
     *
     *   node                     x
     *   /  \      左旋转        /   \
     * T1    x   --------->   node   T3
     *     /  \               /   \
     *    T2   T3            T1   T2
     * @param node
     * @return
     */
    private Node leftRotate(Node node){
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;
        return x;
    }

    /**
     *       node                          x
     *      /   \      右旋转            /    \
     *     x    T2   --------->         y   node
     *    /  \                             /   \
     *   y   T1                           T1   T2
     * @param node
     * @return
     */
    private Node rightRotate(Node node){
        Node x = node.left;

        // 右旋转
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     * 颜色翻转
     * @param node
     */
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 向以node为根的红黑树中插入元素(key, value)，递归算法
     * @param node
     * @param key
     * @param value
     * @return 返回插入新节点后红黑树的根
     */
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);        // 默认插入红色节点
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }

        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }

        return node;
    }

    private boolean isRed(Node node) {
        if (node == null){
            return false;
        }
        return node.color == RED;
    }

    /**
     * 返回以node为根节点的二分搜索树中，key坐在的节点
     * @param node
     * @param key
     * @return
     */
    private Node getNode(Node node, K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }else if(key.compareTo(node.key) < 0){
            return getNode(node.left, key);
        }else{
            return getNode(node.right, key);
        }
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if(node != null){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    /**
     * 删除以node为根的二分搜索树值为e的节点，递归算法
     * 返回删除节点后新的二分搜素输的跟
     * @param node
     * @param key
     * @return
     */
    private Node remove(Node node, K key) {
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
        }else{
            // 带删除节点左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 带删除节点右子树为空
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点左右子树均不为空的情况
            // 找到待删除节点大的最小节点，既待删除节点右子树的最小节点
            // 用这个节点顶替删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;
            return successor;
        }
        return null;
    }

    private Node minimum(Node node) {
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node) {
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exits");
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
        if(FileOperation.readFile("TheUnadjustedGirl.txt", words)){
            System.out.println("Total words: " + words.size());
            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if(map.contains(word)){
                    map.set(word, map.get(word) + 1);
                }else{
                    map.add(word, 1);
                }
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of HUMAN: " + map.get("human"));
        }
    }
}
