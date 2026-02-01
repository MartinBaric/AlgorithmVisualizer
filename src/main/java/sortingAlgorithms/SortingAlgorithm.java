package sortingAlgorithms;

import gui.SwapListener;
import sortingAlgorithms.enums.SortingType;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {
    protected List<T> list;

    protected SortingAlgorithm(List<T> list) {
        this.list = list;
    }
    public abstract void sort(SwapListener<T> swapListener);
    abstract SortingType getType();
}
