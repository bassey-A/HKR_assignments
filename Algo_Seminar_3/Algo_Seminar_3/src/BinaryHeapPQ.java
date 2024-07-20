import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryHeapPQ <X extends Comparable<X>>{
    public int size;                      // present size of heap
    private int capacity;                  // total capacity of the heap
    private List<X> heap;                   // list containing the items in the heap

    public BinaryHeapPQ(){
        this.size = 0;
        this.capacity = 0;
        heap = new ArrayList<>();
    }

    public void add(X node){
        if (node == null) throw new IllegalArgumentException();

        heap.add(size, node);
        capacity++;
        bubbleUp(size);
        size++;
    }

    public void swap(int a, int b){
        // get the items at the positions to be swapped
        X atA = heap.get(a);
        X atB = heap.get(b);

        // swap the items
        heap.set(a, atB);
        heap.set(b, atA);
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public X  removeFromIndex(int z){
        if (isEmpty()) return null;

        X removed = heap.get(z);
        size--;
        swap(z, size);

        // remove last node
        heap.set(size, null);

        //if the last element was removed
        if (z == size) return removed;

        // else we may need to bubble up or down
        X temp = heap.get(z);
        bubbleDown(z);

        //check if that didn't fix the heap
        if (heap.get(z).equals(temp)) bubbleUp(z);
        return removed;
    }

    public boolean removeElement(X element){
        if (element == null) return false;

        // linear search for element
        for (int i = 0; i < size; i++){
            if (element.equals(heap.get(i))){
                removeFromIndex(i);
                return true;
            }
        }
        return false;
    }

    private boolean isLess(int a, int b){
        X atA = heap.get(a);
        X atB = heap.get(b);
        return atA.compareTo(atB) <= 0;
    }

    public void bubbleUp(int z){
        // get parent node using integer division
        int parent = (z - 1) / 2;

        // compare and swap if necessary
        while (z > 0 && isLess(z, parent)){
            swap(z, parent);
            z = parent;

            // get the next parent to compare
            parent = (z - 1) / 2;
        }
    }

    public void bubbleDown(int z){
        while(true){
            int left = (2 * z) + 1;               // left child node (swap here by default)
            int right = (2 * z) + 2;              // right child node
            int smallest = left;

            // compare right and left nodes, set right for swap if smaller
            if (right < size && isLess(right, left)) smallest = right;

            // check if we need to keep going down
            if (left >= size || isLess(z, smallest)) break;

            // bubble down one level
            swap(smallest, z);
            z = smallest;
        }
    }

    public void print(){
        System.out.println(heap);
    }

    public X peek(){
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public X poll(){
        return (removeFromIndex(0));
    }

    public boolean contains(X element){
        for (int i = 0; i < size; i++) if (heap.get(i) == element) return true;
        return false;
    }

    public void linearAdd(X[] elements){
        size = capacity = elements.length;
        heap = new ArrayList<>(capacity);

        // put all elements into the heap
        for (int i = 0; i < size; i++) heap.add(elements[i]);

        for (int i = Math.max(0, (size / 2) - 1); i >= 0; i--) bubbleDown(i);
    }

    public void inOrder(int parent){
        if (parent < size){
            inOrder(parent * 2 + 1);
            System.out.print(heap.get(parent) + " ");
            inOrder(parent * 2 + 2);
        }
    }

    public void preOrder(int parent){
        if (parent < size){
            System.out.print(heap.get(parent) + " ");
            preOrder(parent * 2 + 1);
            preOrder(parent * 2 + 2);
        }
    }

    public void postOrder(int parent){
        if (parent < size){
            postOrder(parent * 2 + 1);
            postOrder(parent * 2 + 2);
            System.out.print(heap.get(parent) + " ");
        }
    }

    public void levelOrder(int parent){
        Queue<Integer> printQueue = new LinkedList<>();
        //add element to queue if valid
        if (parent < size){
            printQueue.add(parent);

            while(!printQueue.isEmpty()){
                // get element at front of queue
                int temp = printQueue.poll();
                System.out.print(heap.get(temp) + " ");

                // add left and right children if they exist
                if ((temp * 2 + 1) < size) printQueue.add(temp * 2 + 1);
                if ((temp * 2 + 2) < size) printQueue.add(temp * 2 + 2);
            }
        }
    }
}
