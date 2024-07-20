// Java program for implementation of Insertion Sort
public class InsertionSort extends Sort{
    public InsertionSort(int[] array) {
        super(array);
    }

    /*Function to sort array using insertion sort*/
    void sort()
    {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

			/* Move elements of array[0..i-1], that are
			greater than key, to one position ahead
			of their current position */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }   
            array[j + 1] = key;
        }
    }

       @Override
    void begin(int arrayMin, int arrayMax) {
        sort();
    }
}


/* This code is contributed by Rajat Mishra. */
