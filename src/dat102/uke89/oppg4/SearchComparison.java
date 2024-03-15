package dat102.uke89.oppg4;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class SearchComparison {
    Set<Integer> hashSet = new HashSet<>();
    Set<Integer> sortedSet = new TreeSet<>();

    int numElements = 100000;
    int startValue = 376;
    int currentValue = startValue;

    public SearchComparison() {
        for (int i = 0; i < numElements; i++) {
            hashSet.add(currentValue);
            sortedSet.add(currentValue);
            currentValue = (currentValue + 45713) % 1000000;
        }
    }

    // Søke Comparison
    public void performSearchComparison() {
        Random random = new Random();
        int numSearches = 1000;
        int numHitsHashSet = 0;
        int numHitsSortedSet = 0;

        // Hashset SØk

        long startTimeHashSet = System.nanoTime();
        for (int i = 0; i < numSearches; i++) {
            int searchKey = random.nextInt(1000000);
            if (hashSet.contains(searchKey)) {
                numHitsHashSet++;
            }
        }
        long endTimeHashSet = System.nanoTime();
        long durationHashSet = endTimeHashSet - startTimeHashSet;

        // TreeSet søk
        long startTimeSortedSet = System.nanoTime();
        for (int i = 0; i < numSearches; i++) {
            int searchKey = random.nextInt(1000000);
            if (sortedSet.contains(searchKey)) {
                numHitsSortedSet++;
            }
        }
        long endTimeSortedSet = System.nanoTime();
        long durationSortedSet = endTimeSortedSet - startTimeSortedSet;

        // Results
        System.out.println("Search Comparison Results: ");
        System.out.println("Hits in HashSet: " + numHitsHashSet);
        System.out.println("Hits in SortedSet: " + numHitsSortedSet);
        System.out.println("Time taken for HashSet search: " + durationHashSet + " nanoseconds");
        System.out.println("Time taken for SortedSet search: " + durationSortedSet + " nanoseconds");
    }

    public static void main(String[] args) {
        SearchComparison comparison = new SearchComparison();
        comparison.performSearchComparison();
    }
}
