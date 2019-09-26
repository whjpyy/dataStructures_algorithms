package ch03.stack;

/**
 * @author carl.z.chen
 * @Date 2019/9/26
 */
public interface Stack<E> {

    /**
     * 栈顶添加元素
     * @param e
     */
    void push(E e);

    /**
     * 栈顶取出元素
     */
    E pop();

    /**
     * 查看栈顶元素
     * @return
     */
    E peek();

    /**
     * 获取栈的长度
     * @return
     */
    int getSize();

    /**
     * 栈是否为空
     * @return
     */
    boolean isEmpty();
}
