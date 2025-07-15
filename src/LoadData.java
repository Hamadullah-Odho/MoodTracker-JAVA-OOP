import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
final public class LoadData {

    private LoadData(){

    }
    public static List<Mood> loadData() {
        List<Mood> moods = new ArrayList<>();
        try {
            File file = new File("mood.txt");
            if (!file.exists()) {
                System.out.println("File does not exist.");
                return moods;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            Mood currentMood = null;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (currentMood != null) {
                        moods.add(currentMood);
                        currentMood = null;
                    }
                } else if (line.startsWith("Mood : ")) {
                    if (currentMood == null) currentMood = new Mood();
                    currentMood.setName(line.substring(7).trim());
                } else if (line.startsWith("Note : ")) {
                    if (currentMood != null) currentMood.setNote(line.substring(7).trim());
                } else if (line.startsWith("Date : ")) {
                    if (currentMood != null) currentMood.setDate(line.substring(7).trim());
                } else if (line.startsWith("Time : ")) {
                    if (currentMood != null) currentMood.setTime(line.substring(7).trim());
                }
            }

            // Add last mood if file didn’t end with a newline
            if (currentMood != null) {
                moods.add(currentMood);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error Reading File: " + e.getMessage());
        }

        return moods;
    }

}
