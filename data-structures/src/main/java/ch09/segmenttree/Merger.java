package ch09.segmenttree;

public interface Merger<E> {
    E merge(E a, E b);
}
