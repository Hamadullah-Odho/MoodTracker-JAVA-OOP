
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDate;

public class MoodTracker {

    public static void main(String[] args) {

        //scanner
        Scanner scan = new Scanner(System.in);

        TrackerSystem system = new TrackerSystem();
        boolean runApplication = true;
        while(runApplication){
            LocalDate date = LocalDate.now();
            System.out.println("Date : " + date);
            System.out.println("\n---- Mood Tracker Console App ----\n");
            System.out.println("a) Add Mood");
            System.out.println("d) Delete Mood");
            System.out.println("s) Search For Mood");
            System.out.println("w) Write on to a file");
            System.out.println("x) Exit Mood Tracker\n");
            System.out.println("--------------------------------------");

            try {
                System.out.print("Input Choice :");
                String userinput = scan.nextLine();

                switch (userinput){
                    case "a":
                        system.addMood();
                        break;
                    case "d":
                        system.deleteMood();
                        break;
                    case "s":
                        system.searchMood();
                        break;
                    case "w":
                        system.saveData();
                        break;
                    case "x":
                        runApplication = false;
                        break;
                    default:
                        System.out.println("Enter Valid Input");
                }
                System.out.println();
            }
            catch (InputMismatchException e){
                System.out.println("Error Occurred : " + e.getMessage());
            }
            catch (Exception e){
                System.out.println("Unexpected Issue ");
                e.getStackTrace();
            }
        }
    }
}
