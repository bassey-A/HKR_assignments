
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;


public class Main {
    static String text = "random numbers.txt";
    static int[] inputSizes = {10000, 100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();
        task4();
    }

    public static void task1(){
        Integer[] elements = {10, 12, 1, 14, 6, 5, 8, 15, 3, 9, 7, 4, 11, 13, 2};
        //Integer [] elements = input_size_test(inputSizes[10]);
        BinaryHeapPQ normal = new BinaryHeapPQ<>();
        for (int x : elements){
            normal.add(x);
        }
        BinaryHeapPQ linear = new BinaryHeapPQ<>();
        linear.linearAdd(elements);

        normal.print();
        linear.print();

        System.out.println("\nin-order");
        System.out.print("Normal:   ");
        normal.inOrder(0);
        System.out.print("\nLinear:   ");
        linear.inOrder(0);

        System.out.println("\n\npre-order");
        System.out.print("Normal:   ");
        normal.preOrder(0);
        System.out.print("\nLinear:   ");
        linear.preOrder(0);

        System.out.println("\n\npost-order");
        System.out.print("Normal:   ");
        normal.postOrder(0);
        System.out.print("\nLinear:   ");
        linear.postOrder(0);

        System.out.println("\n\nlevel-order");
        System.out.print("Normal:   ");
        normal.levelOrder(0);
        System.out.print("\nLinear:   ");
        linear.levelOrder(0);

    }

    private static Integer[] input_size_test(int size){

        ArrayList<Integer> num =  new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get("random numbers.txt"))){
            stream.limit(size).forEach(s -> num.add(Integer.parseInt(s.strip())));
        }catch (IOException err){
            err.printStackTrace();
        }
        return (num.toArray(new Integer[0]));
    }

    public static void task2() {
        Integer[] taskKeys = {4371, 1323, 6173, 4199, 4344, 9679, 1989};
        ChainHashTable chain = new ChainHashTable();
        for (Integer key : taskKeys){
            chain.insert(key);
            System.out.println(chain.getKey(1989));
        }
    }

    public static void task3() {
        Task3WordPuzzle test = new Task3WordPuzzle();
        String[] a = new String[]{"this", "what", "when", "fish"};
        String[] l = new String[]{"this", "hat", "hen"};
        test.findWords(a, l);
    }

    public static void task4() {
        AVLTree avl = new AVLTree();
        RedBlackTree redBlack = new RedBlackTree(0);
        BinarySearchTree bst = new BinarySearchTree();
        BinaryHeapPQ heap = new BinaryHeapPQ<>();

        Integer[] test = input_size_test(7);
        for (Integer i : test){
            System.out.print(i + " ");
        }

        for (Integer i : test){
            avl.insertElement(i);
            redBlack.insertNewNode(i);
            bst.insert(i);
            heap.add(i);
        }

        System.out.println("\n\nTraversals\nAVL");
        avl.inorderTraversal();
        System.out.println("\nBinarySearch");
        bst.inorderTraversal();
        System.out.println("\nRED-BLACK");
        redBlack.inorderTraversal();
        System.out.println("\nHeap");
        heap.inOrder(0);

    }
}