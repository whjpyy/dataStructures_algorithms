package ch12.avltree;

import ch07.set.BSTMap;
import ch07.set.FileOperation;
import ch07.set.Map;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }
    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 获取节点的高度
     * @param node
     * @return
     */
    private int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }

    /**
     * 获得节点node的平衡因子
     * @return
     */
    private int getBalanceFactor(Node node){
        if(node == null){
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 判断该二叉树是否是一个二分搜索树
     * @return
     */
    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if(keys.get(i-1).compareTo(keys.get(i)) > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 中序遍历
     * @param node
     * @param keys
     */
    private void inOrder(Node node, ArrayList<K> keys){
        if(node == null){
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 判断该二叉树是否是一颗平衡二叉树
     * @return
     */
    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if(node == null){
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1){
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //          y                               x
    //        /   \                          /    \
    //       x     T4       向右旋转        z        y
    //      / \          - - - - - - ->   /   \    /  \
    //     z   T3                       T1   T2  T3   T4
    //   /  \
    //  T1  T2
    private Node rightRotate(Node y){
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    /**
     * 左旋转
     * @param y
     * @return
     */
    private Node leftRotate(Node y){
        Node x = y.right;
        Node T2 = x.left;

        // 向右旋转
        x.left = y;
        y.right = T2;

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }


    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key,value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }
        // 更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 平衡维护
        // LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0){
            return rightRotate(node);
        }

        // RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0){
            return leftRotate(node);
        }

        // LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0){
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0){
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
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
        Node retNode;
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            retNode = node;
        }else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            retNode = node;
        }else{
            // 带删除节点左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // 带删除节点右子树为空
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            }
            else {
                // 待删除节点左右子树均不为空的情况
                // 找到待删除节点大的最小节点，既待删除节点右子树的最小节点
                // 用这个节点顶替删除节点的位置
                Node successor = minimum(node.right);
//            successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;
                retNode = successor;
            }
        }

        if(retNode == null){
            return null;
        }

        // 更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护
        // LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0){
            return rightRotate(retNode);
        }

        // RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0){
            return leftRotate(retNode);
        }

        // LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0){
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0){
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
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

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + "doesn't exits");
        }
        node.value = value;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("TheUnadjustedGirl.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> linkedListMap = new AVLTree<>();
            for (String word : words) {
                if (linkedListMap.contains(word)) {
                    linkedListMap.set(word, linkedListMap.get(word) + 1);
                } else {
                    linkedListMap.add(word, 1);
                }
            }
            System.out.println("Total different words: " + linkedListMap.getSize());
            System.out.println("Frequency of HUMAN: " + linkedListMap.get("human"));

            System.out.println("is BST:" + linkedListMap.isBST());
            System.out.println("is Balanced: " + linkedListMap.isBalanced());

            for (String word : words) {
                linkedListMap.remove(word);
                if(!linkedListMap.isBST() || !linkedListMap.isBalanced()){
                    throw new RuntimeException("Error");
                }
            }
        }
    }
}
