import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Tasks {
    public static void LoadTasks(){
        String filename = "../tasks.txt";

        File file = new File(filename);

        if(!file.exists()){
            try{
                file.createNewFile();
                System.out.println("New file tasks.txt created");
            }
            catch(IOException e){
                System.out.println("Something went wrong while creating a file");
            }
        }


        try(BufferedReader br = new BufferedReader(new FileReader(filename))){
            String line;
            while((line = br.readLine()) != null){
                String []data = line.split("\\|");
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
}
