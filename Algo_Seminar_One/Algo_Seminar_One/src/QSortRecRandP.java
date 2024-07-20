public class QSortRecRandP extends Sort{
    public QSortRecRandP(int[] array) {
        super(array);
    }

    @Override
    void begin(int low, int high) {
        quicksort(low, high);
        }

    int partition(int low, int high){
        // rIndex gives the random index between low and
        // high (both inclusive)

        int rIndex = (low) + (int)(Math.random() * (high - low + 1));
        swap(low, rIndex);  // swap low with random index

        int pivot = array[low], i = low - 1, j = high + 1;
        while(true) {
            // increase i while elements are less than pivot
            do {
                i++;
            } while (array[i] < pivot);

            // decrease j while elements are greater than pivot
            do {
                j--;
            } while (array[j] > pivot);

            if (i >= j) return j; // when both pointers meet, elements are at their correct place for now
            swap(i, j);   // swap i and j, since both are not at their correct index
        }
    }

    void quicksort(int low, int high){
        if (low < high){
            // find partition index
            int p = partition(low, high);
            // sort before and after the pivot
            quicksort(low, p);
            quicksort(p + 1, high);
        }
    }
}
