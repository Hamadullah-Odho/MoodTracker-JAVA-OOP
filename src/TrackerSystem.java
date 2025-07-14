import java.util.*;
import java.io.*;
import java.time.LocalTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
final public class TrackerSystem {
    Scanner scan = new Scanner(System.in);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm-ss");
    List<Mood> mood = new ArrayList<>();
    public void addMood(){
        try{
            System.out.println("--- ADD MOOD MENU ---\n");

            //Local Date and Time
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();

            // User input for mood
            System.out.print("Enter mood name :");
            String name = scan.nextLine();

            //Checking if mood already exist on same date
            boolean notExist = true;
            for(Mood m : mood){
                if(m.getName().equalsIgnoreCase(name) && m.getDate().equals(String.valueOf(date))){
                    System.out.println("Mood Already Exist");
                    notExist = false;
                }
            }
            //if mood does not exist
            if(notExist){
                System.out.print("Enter Note : ");
                String note = scan.nextLine();
                Mood currentMood = new Mood();
                currentMood.setTime(String.valueOf(time));
                currentMood.setDate(String.valueOf(date));
                currentMood.setName(name);
                currentMood.setNote(note);
                mood.add(currentMood);
                System.out.println("Mood Added Successfully");
            }
        }
        catch(InputMismatchException e){
            System.out.println("Error : " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Unexpected Error : " + e.getMessage());
        }
    }

    //method for deleting existing mood
    public void deleteMood(){

        try{

            System.out.println("---- Mood Delete ----\n");
            System.out.println("a) Delete mood on particular date");
            System.out.println("b) Delete mood on by details");
            System.out.print("Enter Choice : ");
            String userinput = scan.nextLine();

            switch (userinput) {
                case "a":
                    System.out.print("Enter Date in format <yyyy-MM-dd>: ");
                    String removeOnDate = scan.nextLine();
                    boolean noMoods = true;

                    Iterator<Mood> iterator = mood.iterator();

                    while (iterator.hasNext()){

                        Mood current = iterator.next();

                        if(current.getDate().equalsIgnoreCase(removeOnDate)){
                            iterator.remove();
                            noMoods = false;
                        }
                    }
                    if (noMoods) {
                        System.out.println("No moods exist on this date ");
                    } else {
                        System.out.println("All moods deleted for this date");
                    }
                    break;
                case "b":
                    System.out.print("Enter mood name :");
                    String removeMood = scan.nextLine();
                    System.out.print("Enter Date in format <yyyy-MM-dd>: ");
                    removeOnDate = scan.nextLine();
                    noMoods = true;
                    Iterator<Mood> iterator1 = mood.iterator();
                    while (iterator1.hasNext()){
                        Mood current = iterator1.next();
                        if(current.getName().equalsIgnoreCase(removeMood) && current.getDate().equalsIgnoreCase(removeOnDate)){
                            iterator1.remove();
                            noMoods = false;
                        }
                    }
                    if(noMoods){
                        System.out.println("No Mood Found");
                    }
                    else{
                        System.out.println("Mood deleted");
                    }
                    break;
                default:
                    System.out.println("Enter Valid Choice");
            }

        }
        catch(InputMismatchException e){
            System.out.println("Error : " + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Unexpected Error : " + e.getMessage());
        }
    }

    //Method for Searching mood

    public void searchMood(){

        try{
            System.out.println("---- Search Mood ----\n");
            System.out.println("a) Search moods on particular date");
            System.out.println("b) Search mood by details");
            System.out.print("Enter Choice : ");
            String userinput = scan.nextLine();

            switch (userinput) {
                case "a":
                    System.out.print("Enter Date in format <yyyy-MM-dd> :");
                    String searchOnDate = scan.nextLine();
                    boolean noMoods = true;
                    int moodCount = 1;
                    for (Mood m : mood) {
                        if (m.getDate().equalsIgnoreCase(searchOnDate)) {
                            System.out.println("Mood :" + moodCount);
                            System.out.println("Mood name : " + m.getName());
                            System.out.println("Note : " + m.getNote());
                            System.out.println();
                            noMoods = false;
                            moodCount++;
                        }
                    }
                    if (noMoods) {
                        System.out.println("No moods on this date");
                    }
                    break;
                case "b":
                    System.out.print("Enter mood :");
                    String name = scan.nextLine();
                    System.out.print("Enter Date in format <yyyy-MM-dd> :");
                    searchOnDate = scan.nextLine();
                    boolean notFound = true;
                    for(Mood m : mood){
                        if(m.getDate().equalsIgnoreCase(searchOnDate) && m.getName().equalsIgnoreCase(name)){
                            System.out.println("Mood : " + m.getName());
                            System.out.println("Note : " + m.getNote());
                            System.out.println("Date : " + m.getDate());
                            System.out.println("Time : " + m.getTime());
                            notFound = false;
                            break;
                        }
                    }
                    if(notFound){
                        System.out.println("mood not found");
                    }
                    break;
                default:
                    System.out.println("Enter valid choice");
            }

        }
        catch (InputMismatchException e){
            System.out.println("Error :" + e.getMessage());
        }
        catch (Exception e){
            System.out.println("Unexpected error : " + e.getMessage());
        }
    }

    //method for saving data to .txt file
    public void saveData(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("mood.txt"));
            int moodCount = 1;
            for(Mood m : mood){
                writer.write(String.valueOf(moodCount));
                writer.newLine();
                writer.write("Mood : " + m.getName());
                writer.newLine();
                writer.write("Note : " + m.getNote());
                writer.newLine();
                writer.write("Date : " + m.getDate());
                writer.newLine();
                writer.write("Time : " + m.getTime());
                writer.newLine();
                writer.newLine();
                moodCount++;
            }
            System.out.println("Data Write Successfully");
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error Writing File : " + e.getMessage() );
        }
    }

}
