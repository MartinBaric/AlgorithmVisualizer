package sortingAlgorithms;

import lombok.Setter;
import sortingAlgorithms.functionalInterfaces.HighlightListener;
import sortingAlgorithms.functionalInterfaces.SwapListener;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {
    @Setter
    protected List<T> list;
    protected HighlightListener highlightListener = null;
    protected SwapListener swapListener = null;

    public SortingAlgorithm(List<T> list, HighlightListener highlightListener, SwapListener swapListener) {
        this.list = list;
        this.highlightListener = highlightListener;
        this.swapListener = swapListener;
    }
    public SortingAlgorithm() {}
    public abstract void sort();
}
