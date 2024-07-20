import java.util.Arrays;

public class BinarySearch extends Sort{
    public BinarySearch(int[] array) {
        super(array);
    }

    int binarySearch(int arr[], int low, int high, int find)
    {
        if (high >= low) {
            int mid = low + (high - low) / 2;

            // If the element is present at the
            // middle itself
            if (arr[mid] == find)
                return mid;

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (arr[mid] > find)
                return binarySearch(arr, low, mid - 1, find);

            // Else the element can only be present
            // in right subarray
            return binarySearch(arr, mid + 1, high, find);
        }

        // We reach here when element is not present
        // in array
        return -1;
    }
    @Override
    void begin(int arrMin, int arrMax) {
        Arrays.sort(array);
        System.out.println(String.format("Array length: %d,\tSearch term: %d", array.length, arrMin));
        int result = binarySearch(array, 0, array.length-1, arrMin);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at index "
                    + result);
        System.out.println();
    }
}
