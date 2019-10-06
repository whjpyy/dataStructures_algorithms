package ch02.array;

/**
 * @author carl.z.chen
 * @Date 2019/9/26
 */
public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(E[] arr){
        data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = data.length;
    }

    public Array(){
        this(10);
    }

    /**
     * 获取数组的长度
     * @return
     */
    public int getSize(){
        return size;
    }

    /**
     * 获取数组的容量
     * @return
     */
    public int getCapacity(){
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 从数组末尾添加一个元素
     * @param e
     */
    public void addLast(E e){
//        if(size == data.length){
//            throw new IllegalArgumentException("AddLast is failed. Array is full.");
//        }
//        data[size] = e;
//        size++;
        add(size, e);
    }

    /**
     * 在数组队首添加一个元素
     * @param e
     */
    public void addFirst(E e){
        add(0, e);
    }

    /**
     * 在数组指定位置添加一个元素
     * @param index
     * @param e
     */
    public void add(int index, E e){
//        if(size == data.length){
//            throw new IllegalArgumentException("AddLast is failed. Array is full.");
//        }
        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add is failed. require index >= 0 and index <= size.");
        }

        if(size == data.length){
            resize(data.length * 2);
        }

        // 在索引位置后面的元素都往后移动一位
        for (int i = size - 1; i >= index ; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 数组扩容
     * @param newCapacity
     */
    private void resize(int newCapacity) {
        E[] newArray = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    /**
     * 获取index索引位置的元素
     * @param index
     * @return
     */
    public E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed. index is illegal.");
        }
        return data[index];
    }

    /**
     * 获取数组最后一个位置的元素
     * @return
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * 获取数组第一个位置的索引
     * @return
     */
    public E getFirst(){
        return get(0);
    }

    /**
     * 修改index索引位置的元素
     * @param index
     * @param e
     */
    public void set(int index, E e){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Set failed. index is illegal.");
        }
        data[index] = e;
    }

    /**
     * 查找数组中是否包含元素e
     * @param e
     * @return
     */
    public boolean contains(E e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return true;
            }
        }
        return false;
    }

    /**
     * 查找元素e在数组的索引位置
     * @param e
     * @return
     */
    public int find(E e){
        for (int i = 0; i < size; i++) {
            if(data[i] == e){
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除index索引位置的元素
     * @param index
     * @return
     */
    public E remove(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. index is illegal.");
        }
        E ret = data[index];
        // 从index索引的下一位开始都往左边移动一位
        for (int i = index; i < size - 1;i ++){
            data[i] = data[i + 1];
        }
        size--;

        // 缩容
        if(size == data.length / 4 && data.length / 2 != 0){
            resize(data.length / 2);
        }
        return ret;
    }

    /**
     * 删除数组的第一个元素
     * @return
     */
    public E removeFirst(){
        return remove(0);
    }

    /**
     * 删除数组的最后一个元素
     * @return
     */
    public E removeLast(){
        return remove(size - 1);
    }

    /**
     * 删除指定元素e
     * @param e
     */
    public void removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
        }
    }

    /**
     * 交换2个元素的值
     * @param i
     * @param j
     */
    public void swap(int i, int j){
        if(i < 0 || i >= size || j < 0 || j >= size){
            throw new IllegalArgumentException("Index is illegal.");
        }
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append( String.format("Array: size = %d, capacity = %d.\n", getSize(), getCapacity()));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if(i != size - 1){
                res.append(",");
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        Array array = new Array();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(1, 100);
        System.out.println(array);

        array.addFirst(-1);
        System.out.println(array);

        array.remove(2);
        System.out.println(array);

        array.removeFirst();
        System.out.println(array);

        array.removeElement(5);
        System.out.println(array);
    }
}
