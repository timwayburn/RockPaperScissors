package app;

import java.io.*;

/**
 * Created by Stach on 2016-06-09.
 */
public class HighScoreManager {
    private String saveDataPath;
    private String fileName = "SaveData";

    public HighScoreManager(String saveDataPath) {
        this.saveDataPath = saveDataPath;
    }

    public void setNewHighScore(String name, int score) {
        FileWriter output;
        try{
            File f = new File(saveDataPath,fileName);
            output = new FileWriter(f);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write(name + " " + score);

            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getHighScoreName(){
        try{
            File f = new File(saveDataPath, fileName);
            if(!f.isFile()){
                createSaveData();
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String topscore = reader.readLine();
            String[] topScoreParts = topscore.split("\\s");
            String highScoreName = topScoreParts[0];
            reader.close();
            return highScoreName;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "NOONE";
    }

    public int getHighScore(){
        try{
        File f = new File(saveDataPath, fileName);
        if(!f.isFile()){
            createSaveData();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String topscore = reader.readLine();
        String[] topScoreParts = topscore.split("\\s");
        int highScore = Integer.parseInt(topScoreParts[1]);
        reader.close();
        return highScore;
    }
    catch(Exception e){
        e.printStackTrace();
    }
       return 0;
    }

    private void createSaveData(){
        try{
            File file = new File(saveDataPath, fileName);

            FileWriter output = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(output);
            writer.write("NOONE " + 000);
            writer.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
