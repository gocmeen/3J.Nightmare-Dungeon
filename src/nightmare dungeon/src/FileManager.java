import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileManager {
    public String[] highScores = new String[10];
    BufferedReader br = null;
    FileReader fr = null;

    public FileManager(String filename){
        readFromFile(filename);
    }

    public void readFromFile(String filename){
        try {
            fr = new FileReader(filename);
            br = new BufferedReader(fr);
            String currentLine;
            int i = 0;
            while ((currentLine = br.readLine()) != null) {
                highScores[i] = currentLine;
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
