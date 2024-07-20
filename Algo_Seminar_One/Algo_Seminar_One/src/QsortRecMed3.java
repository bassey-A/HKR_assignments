public class QsortRecMed3 extends Sort{
    public QsortRecMed3(int[] array) {
        super(array);
    }

    @Override
    void begin(int low, int high){
        quicksort(low, high);
    }

    void quicksort(int low, int high) {
        int i = low; int j = high;
        // Get the pivot element from the middle of the list
        int pivot = array[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (array[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (array[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the values.
            // As we are done we can increase i and j
            if (i <= j) {
                swap(i, j);
                i++;
                j--;
            }
        }

        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
        }
}
