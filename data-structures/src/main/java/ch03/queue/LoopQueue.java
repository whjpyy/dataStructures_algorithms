package ch03.queue;

/**
 * @author carl.z.chen
 * @Date 2019/9/26
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue(){
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity(){
        return data.length - 1;
    }

    @Override
    public void enquque(E e) {
        if((tail + 1) % data.length == front){
            resize(data.length * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newArray = (E[])new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newArray[i] = data[(i + front) % data.length];
        }
        data = newArray;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            throw new IllegalArgumentException("Dequeue failed.Queue is empty.");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        // 缩容
        if(size == getCapacity() / 4  && getCapacity() / 2 != 0){
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()){
            throw new IllegalArgumentException("GetFront failed.Queue is empty.");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: size=" + size + ", capacity=" + getCapacity() + "\n" +
                "front [");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            if((i + 1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> loopQueue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            loopQueue.enquque(i);
            System.out.println(loopQueue);

            if(i % 3 == 2){
                loopQueue.dequeue();
                System.out.println(loopQueue);
            }
        }
    }
}
