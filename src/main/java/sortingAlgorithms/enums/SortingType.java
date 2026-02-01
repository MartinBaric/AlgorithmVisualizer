package sortingAlgorithms.enums;

public enum SortingType {
    BUBBLESORT("Bubblesort");

    private final String name;

    SortingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
