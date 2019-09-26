package ch03.queue;

/**
 * 队列接口
 * @param <E>
 */
public interface Queue<E> {

    /**
     * 获取队列数量
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 添加到队列尾部
     */
    void enquque(E e);

    /**
     * 删除队列头的元素
     * @return
     */
    E dequeue();

    /**
     * 查看队列头的元素
     * @return
     */
    E getFront();
}
