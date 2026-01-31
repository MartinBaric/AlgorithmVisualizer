package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;

import java.util.List;

public abstract class SortingAlgorithm<T extends Comparable<T>> {
    protected List<T> list;

    protected SortingAlgorithm(List<T> list) {
        this.list = list;
    }
    abstract void sort();
    abstract SortingType getType();
}
