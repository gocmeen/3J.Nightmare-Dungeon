import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

public class FileManager {
    public String[] highScores = new String[10];
    public String[] scores = new String[10];
    public String[] names = new String[10];
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
                names[i] = currentLine.substring(currentLine.indexOf(")") + 1, currentLine.indexOf("."));
                scores[i] = currentLine.substring(currentLine.lastIndexOf(".") + 1);
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
