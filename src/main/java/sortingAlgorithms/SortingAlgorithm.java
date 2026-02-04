package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {
    protected List<T> list;

    protected SortingAlgorithm(List<T> list) {
        this.list = list;
    }
    public abstract void sort(SwapListener swapListener, HighlightListener highlightListener);
    abstract SortingType getType();
}
