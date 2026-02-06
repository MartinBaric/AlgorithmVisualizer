package sortingAlgorithms.enums;

public enum SortingType {
    BUBBLESORT("Bubblesort"),
    INSERTSORT("Insertsort");

    private final String name;

    SortingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
