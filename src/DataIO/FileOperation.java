package DataIO;

import java.io.FileWriter;
import java.io.IOException;

public class FileOperation {

    public static void writeFile(String data, String fileDestination){
        try {
            FileWriter myWriter = new FileWriter(fileDestination);
            myWriter.write(data);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}