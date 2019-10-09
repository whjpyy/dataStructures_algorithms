package ch08.heap;

import ch02.array.Array;

import java.util.Random;

public class MinHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MinHeap(int capacity){
        data = new Array<>(capacity);
    }
    public MinHeap(){
        data = new Array<>();
    }

    /**
     * 把任意一个数组转换成堆
     * @param arr
     */
    public MinHeap(E[] arr){
        data = new Array<>(arr);
        // 从最后一个节点的父节点开始进行下沉操作
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    /**
     * 返回元素个数
     * @return
     */
    public int getSize(){
        return data.getSize();
    }

    /**
     * 是否为空
     * @return
     */
    public boolean isEmpty(){
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index){
        if(index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index){
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index){
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     * @param e
     */
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮
     * @param k
     */
    private void siftUp(int k) {
        // 父节点大于于指定的索引的值，2个元素交换位置
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0){
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查看堆中的最大元素
     * @return
     */
    public E findMin(){
        if(data.getSize() == 0){
            throw new IllegalArgumentException("Heap is empty.");
        }
        return data.get(0);
    }

    public E extractMin(){
        E ret = findMin();

        data.swap(0, data.getSize() - 1);
        data.removeLast();
        shiftDown(0);

        return ret;
    }

    /**
     * 下沉
     * @param k
     */
    private void shiftDown(int k) {
        while (leftChild(k) < data.getSize()){
            int j = leftChild(k); // 左孩子索引
            // 先比较左右孩子，得到最小值
            // 存在右孩子节点，其右孩子节点比左孩子节点大
            if(j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) < 0){
                j = rightChild(k);
            }
            // 再与k坐在的索引比较,如果k小，则满足二叉堆的定义，中断循环退出索引
            if(data.get(k).compareTo(data.get(j)) <= 0){
                break;
            }
            // 替换k与j的值，继续下沉
            data.swap(k, j);
            k = j;
        }
    }

    /**
     * 取出堆中最大的元素，并且替换成元素e
     * @return
     */
    public E replace(E e){
        E ret = findMin();
        data.set(0, e);
        shiftDown(0);
        return ret;
    }

    public static void main(String[] args) {
        int n = 100000;
        MinHeap<Integer> maxHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMin();
        }

        for (int i = 1; i < n; i++) {
            if(arr[i - 1] > arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MinHeap complete.");
    }
}
