package ch11.unionfind;

public interface UF {

    int getSize();

    /**
     * 查看元素p和元素q是否所属一个集合
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 合并元素p和元素q所属的集合
     * @param p
     * @param q
     */
    void unionElements(int p, int q);
}
