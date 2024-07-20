//javarevisited.blogspot.com/2016/09/iterative-quicksort-example-in-java-without-recursion.html#ixzz7lBAhZ3p3

import java.util.Stack;

public class QSortIteMed3 extends Sort{
    public QSortIteMed3(int[] array) {
        super(array);
    }

    int partition(int pivot, int start, int end){
        int l = start;
        int h = end - 2;
        int piv = array[pivot];
        swap(pivot, end - 1);
        while (l < h) {
            if (array[l] < piv) {
                l++;
            } else if (array[h] >= piv) {
                h--;
            } else {
                swap(l, h);
            }
        }
        int idx = h;
        if (array[h] < piv) {
            idx++;
        }
        swap(end - 1, idx);
        return idx;
    }

    @Override
    void begin(int arrMin, int arrMax) {
        Stack stack = new Stack();
        stack.push(0);
        stack.push(array.length);
        while (!stack.isEmpty()) {
            int end = (int) stack.pop();
            int start = (int) stack.pop();
            if (end - start < 2) {
                continue;
            }
            int pivot = start + ((end - start) / 2);
            pivot = partition(pivot, start, end);
            stack.push(pivot + 1);
            stack.push(end);
            stack.push(start);
            stack.push(pivot);
        }
    }
}
