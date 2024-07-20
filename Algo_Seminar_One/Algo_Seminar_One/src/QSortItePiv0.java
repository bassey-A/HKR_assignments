// https://www.techiedelight.com/iterative-implementation-of-quicksort/

import java.util.Stack;

public class QSortItePiv0 extends Sort{
    public QSortItePiv0(int[] array) {
        super(array);
    }

    int partition(int low, int high){
        int pivot = array[low];

        // index of smaller element
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (array[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                swap(i,j);
            }
        }

        // swap arr[i] and arr[low] (or pivot)
        //int temp = array[i];
        //array[i] = array[low];
        //array[low] = temp;
        swap(i,low);

        return i;
    }

    @Override
    void begin(int low, int high) {
        // Create a stack
        int[] stack = new int[high - low + 1];

        // initialize top of stack
        int top = -1;

        // push initial values of l and h to stack
        stack[++top] = low;
        stack[++top] = high;

        // Keep popping from stack while is not empty
        while (top >= 0) {
            // Pop h and l
            high = stack[top--];
            low = stack[top--];

            // Set pivot element at its correct position
            // in sorted array
            int p = partition(low, high);

            // If there are elements on left side of pivot,
            // then push left side to stack
            if (p - 1 > low) {
                stack[++top] = low;
                stack[++top] = p - 1;
            }

            // If there are elements on right side of pivot,
            // then push right side to stack
            if (p + 1 < high) {
                stack[++top] = p + 1;
                stack[++top] = high;
            }
        }
    }
}
