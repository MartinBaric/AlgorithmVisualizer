package gui;

@FunctionalInterface
public interface SwapListener<T extends Comparable<T>> {
    void swap(int i, int j);
}
