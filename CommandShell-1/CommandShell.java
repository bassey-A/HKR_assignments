package CommandShell;

import java.io.*;
import java.util.*;

public class CommandShell {
     static Scanner scanner = new Scanner(System.in);
    static void createProcess(String command) throws java.io.IOException {

        List<String> input = Arrays.asList(command.split(" "));

        ProcessBuilder processBuilder = new ProcessBuilder(input);
        BufferedReader bufferReader = null;
        try {

            Process proc = processBuilder.start();
            InputStream inputStream = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream);
            bufferReader = new BufferedReader(isr);

            String line;
            while ((line = bufferReader.readLine()) != null) {
                System.out.println(line );
            }
            bufferReader.close();
        } catch (java.io.IOException ioe) {
            System.err.println("Error");
            System.err.println(ioe);
        } finally {
            if (bufferReader != null) {
                bufferReader.close();
            }
        }
    }

    public static void main(String[] args) throws java.io.IOException {
        String commandLine;
        //Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n***** Welcome to the Java Command Shell *****");
        System.out.println("If you want to exit the shell, type END and press RETURN.\n");
    
        while (true) {
               System.out.print("jsh>");
            commandLine = scanner.nextLine();
            // if user entered a return, just loop again
            if (commandLine.equals("")) {
                continue;
            }
            if (commandLine.toLowerCase().equals("end")) { //User wants to end shell
                System.out.println("\n***** Command Shell Terminated. See you next time. BYE for now. *****\n");
                scanner.close();
                System.exit(0);
            }else{
                String[] arguments = commandLine.split(" ");


                if (arguments[0].equalsIgnoreCase("filedump")){
                    try{
                        String path = commandLine.substring((commandLine.indexOf(" ")) +1);

                        task1(commandLine.substring((commandLine.indexOf(" ")) +1));
                    }catch (StringIndexOutOfBoundsException e){
                        e.printStackTrace();
                    }
                }else if(arguments[0].equalsIgnoreCase("copyfile")){
                    String source = commandLine.substring(((commandLine.indexOf(" ")) +1), (commandLine.lastIndexOf(" ")));
                    String destination = commandLine.substring (commandLine.lastIndexOf(" ") +1);
                    task2(source, destination);
                }else if(arguments[0].equalsIgnoreCase("appendfile")){
                    String source = commandLine.substring(((commandLine.indexOf(" ")) +1), (commandLine.lastIndexOf(" ")));
                    String destination = commandLine.substring (commandLine.lastIndexOf(" ") +1);
                    task3(source, destination);
                    /*
                    * The new command for task 3 is the "appendfile" command.
                    * This command works similar to the copyfile command from task 2.
                    * The key difference between both of them is that in task 2, the existing
                    * file is overwritten by the text copied from the source file.
                    * But by using this command, the contents of the destination file are preserved.
                    * The text from the source file is added to a new line at the end of the destination file.
                    * Also, in copyfile, the destination file is created if it does not exist.
                    * But in appendfile, the destination file is not created if it does not exist.
                    * I made it this way because I assumed the user would want to add to an existing file.
                    * For the append operation to work, I check to make sure both the files exist.
                    */
                }
            }

        }   
    }

    public static void task1(String text){
        File file = new File(text);
        try {
            if (!file.exists()) {
                System.err.println("File Not Found!" +
                        "\nCheck filename.");
            } else {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    System.out.println(fileReader.nextLine());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void task2(String src, String dst) throws IOException {
        File source = new File(src);
        Scanner fileReader = new Scanner(source);
        if (!source.exists()){
            System.err.println(source + "not found! Check the filename.");

        }else{
            File destination = new File(dst);
            if (!destination.exists()){
                destination.createNewFile();
            }
            FileWriter fw = new FileWriter(destination);
            while (fileReader.hasNextLine()){
                fw.write(fileReader.nextLine() + "\n");
            }
            fw.close();
        }
    }

    public static void task3(String src, String dst) throws IOException {
        File source = new File(src);
        if (!source.exists()){
            System.err.println("Source file not found.");
        }
        File destination = new File(dst);
        if (!destination.exists()){
            System.err.println("Destination file not found.");
        }
        if ((source.exists()) && (destination.exists())){
            Scanner fileReader = new Scanner(source);
            FileWriter fw = new FileWriter(destination, true);
            while (fileReader.hasNextLine()){
                fw.append(fileReader.nextLine() + "\n");
            }
            fw.close();
        }
    }
   
}
