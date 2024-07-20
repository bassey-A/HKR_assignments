import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;



public class Main {
    static String text = "random numbers.txt";
    static Random rand = new Random();
    public static void main(String[] args) throws IOException {
        int[] inputSizes = {200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000};
        //int[] inputSizes = {15};
        for(int a : inputSizes){
            float totalTime = 0;
            int runs = 10;
            for (int p = 1; p <= runs; p++){
                int size = a;
                float elapsedTime;
                QSortIteMed3 test = new QSortIteMed3(makeArray(size));
                //test.display();
                long startTime = System.nanoTime();
                test.begin(0, size-1);
                long endTime = System.nanoTime();
                elapsedTime = ((float)(endTime-startTime) / 1000000);
                //test.display();
                //System.out.println("Time = " + elapsedTime);                ///*
                totalTime += elapsedTime;
                if (p == 1){
                    test.writer("\nInput size\t" + size + "\n");
                }
                test.writer(elapsedTime + "\t\t");
                if (p == runs) {
                    test.writer("\nAverage time:\t" + (totalTime / p) + "\n");
                }                                                               //*/
            }
        }
    }


    public static int[] makeArray(int size) throws IOException {
        Random seed = new Random();
        List<Integer> num = new ArrayList<>();

        while (num.size() < size){
            try (Stream<String> numbers= Files.lines(Paths.get(text))){
                numbers.skip(seed.nextInt(size)).limit(size - num.size()).forEach(s -> num.add(Integer.parseInt(s.strip())));
            }
        }
        // convert arraylist to array, while preventing null pointer exceptions
        return (num.stream().limit(size).filter(i -> i != null).mapToInt(i -> i).toArray());
        }

}