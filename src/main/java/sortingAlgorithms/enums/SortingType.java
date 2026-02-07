package sortingAlgorithms.enums;

import lombok.Getter;

@Getter
public enum SortingType {
    BUBBLESORT("Bubblesort"),
    INSERTSORT("Insertsort"),
    SELECTSORT("Selectsort");

    private final String name;

    SortingType(String name) {
        this.name = name;
    }
}
