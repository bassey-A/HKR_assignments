import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


abstract class Sort{
    protected int[] array;
    protected int arrayLength;


    public Sort(int[] array){
        this.array = array;

    }

    abstract void begin(int arrMin, int arrMax);
    public void swap(int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

    }

    public void display() {
        String del = "\t";
        for (int y : array) {
            System.out.print(String.format("%2d%s",y, del));
        }
        System.out.println();
    }

    public void writer(String time) throws IOException {
        File output = new File("output.txt");
        output.createNewFile();
        FileWriter w = new FileWriter(output, true);
        BufferedWriter write = new BufferedWriter(w);
        write.write(time);
        write.close();
    }
}
