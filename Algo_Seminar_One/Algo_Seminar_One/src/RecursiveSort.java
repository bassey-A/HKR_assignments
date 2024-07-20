abstract class RecursiveSort {
    public int[] array;
    public int arrayLength;

    public RecursiveSort(int[] array){
        this.array = array;
        this.arrayLength = array.length;
    }

    abstract int getPivot();

    public void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void begin(int arrMin, int arrMax){
        int pivot = getPivot();
    }

    public void partition(int low, int high){
        int pivot = getPivot();
        swap(low, pivot);
    }

    public void display() {
        String del = "\t";
        for (int y : array) {
            System.out.print(String.format("%2d%s",y, del));
        }
        System.out.println();
    }
}
