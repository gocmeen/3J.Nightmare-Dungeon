import java.io.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class FileManager {
    public static String[] highScores = new String[11];
    public static String[] scores = new String[11];
    public static String[] names = new String[11];
    static BufferedReader br = null;
    static FileReader fr = null;
    static PrintWriter writer = null;

    public FileManager(){

    }

    public static void readFromFile(String filename){
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
                if (i == 9)
                    FileManager.scores[10] = String.valueOf(FileManager.scores[i]);
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

    public static void writeToFile(String filename){
        try {
            writer = new PrintWriter(filename);
        }catch(FileNotFoundException exc){
            System.out.println("File not found");
        }
        writer.println(names[0] + "...." + scores[0]);
        writer.println(names[1] + "...." + scores[1]);
        writer.println(names[2] + "...." + scores[2]);
        writer.println(names[3] + "...." + scores[3]);
        writer.println(names[4] + "...." + scores[4]);
        writer.println(names[5] + "...." + scores[5]);
        writer.println(names[6] + "...." + scores[6]);
        writer.println(names[7] + "...." + scores[7]);
        writer.println(names[8] + "...." + scores[8]);
        writer.println(names[9] + "...." + scores[9]);
        writer.close();
    }
}
