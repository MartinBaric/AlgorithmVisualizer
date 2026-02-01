package sortingAlgorithms;

import sortingAlgorithms.enums.SortingType;

import java.util.List;

public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    protected BubbleSort(List<T> list) {
        super(list);
    }

    @Override
    void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {

                if (list.get(j - 1).compareTo(list.get(j)) > 0) {
                    // Ascending bubble
                    T temp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, temp);
                }
            }
        }
    }

    @Override
    SortingType getType() {
        return SortingType.BUBBLESORT;
    }
}
