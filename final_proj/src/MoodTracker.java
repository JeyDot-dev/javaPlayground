import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class MoodTracker {
    public static void main(String[] args) {
        ArrayList<Mood> moods = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Press 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");
            String menuOption = input.nextLine();
            switch (menuOption) {
                case "a":
                    try {
                        addMood(input, moods);
                        System.out.println("Mood added successfully!");
                    }
                    catch(Exception e) {System.out.println(e.getMessage());}
                    continue;
                case "d":
                    try {
                        deleteMoods(input, moods);
                        System.out.println("Mood(s) deleted successfully!");
                    }
                    catch(Exception e) {System.out.println(e.getMessage());}
                    continue;
                case "e":
                    try {
                        editMoodNote(input, moods);
                        System.out.println("Mood edited successfully!");}
                    catch(Exception e) {System.out.println(e.getMessage());}
                    continue;
                case "s":
                    try {
                        searchMoods(input, moods);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    continue;
                case "M":
                    getAllMoods(moods);
                    continue;
                case "w":
                    writeMoodsToFile(moods);
                    continue;
                case "Exit":
                    System.out.println("Thank you for using the MoodTracker. Goodbye!");
                    return;
                default:
                    System.out.println("Not a valid input!");
                    continue;
            }
        }
    }

    private static boolean isMoodValid(Mood moodToCheck, ArrayList<Mood> moodsList) throws InvalidMoodException {
        for (Mood mood: moodsList){
            if (mood.equals(moodToCheck)){
                throw new InvalidMoodException("Mood already exists!");
            }
        }
        return true;
    }

    private static void    addMood(Scanner input, ArrayList<Mood> moodsList){
        String              moodName;
        String              note;
        Mood                newMood;
        LocalTime           time;
        LocalDate           date;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println("Enter the mood name");
        moodName = input.nextLine();

        System.out.println("Input the date in the format of MM/dd/yyyy or press ENTER to use the current date");
        String dateInput = input.nextLine();
        date = !dateInput.isEmpty() ? LocalDate.parse(dateInput, dateFormatter) : LocalDate.now();

        System.out.println("Input the time in the format of HH:mm:ss or press ENTER to use the current time");
        String timeInput = input.nextLine();
        time = !timeInput.isEmpty() ? LocalTime.parse(timeInput) : LocalTime.now();

        System.out.println("Write any notes you have for the mood or press ENTER if you don't have any");
        note = input.nextLine();
        newMood = new Mood(moodName, date, time, note);
        if (isMoodValid(newMood, moodsList)){
            moodsList.add(newMood);
        }
    }
    private static void deleteMoodsByDate(LocalDate date, ArrayList<Mood> moodsList){
        boolean removed = false;
        for (Mood mood : moodsList){
            if (mood.getDate().equals(date)){
                moodsList.remove(mood);
                removed = true;
            }
        }
        if (!removed) throw new InvalidMoodException("No moods found for the given date");
    }
    private static void deleteMoods(Scanner input, ArrayList<Mood> moodsList){
        String      choice;
        String      moodName;
        LocalDate   date;
        LocalTime   time;

        System.out.println("Would you like to:\n" +
                "1. Delete a specific mood\n2. Delete all moods on a specific date\n" +
                "(Enter \"1\" or \"2\")");
        choice = input.nextLine();
        switch (choice){
            case "1":
                System.out.println("Enter the NAME of the mood you want to delete");
                moodName = input.nextLine();
                System.out.println("Enter the DATE, in MM/dd/yyyy format, of the mood you want to delete");
                date = LocalDate.parse(input.nextLine());
                System.out.println("Enter the TIME, in HH:mm:ss format, of the mood you want to delete");
                time = LocalTime.parse(input.nextLine());
                if(!moodsList.remove(new Mood(moodName, date, time))){
                    throw new InvalidMoodException("Mood not found for deletion");
                }
                return;
            case "2":
                System.out.println("Enter the DATE, in MM/dd/yyyy, format of the moods you want to delete");
                date = LocalDate.parse(input.nextLine());
                deleteMoodsByDate(date, moodsList);
                return;
        }
        throw new InvalidMoodException("Invalid input. You must choose either 1 or 2");
    }
    private static void editMoodNote(Scanner input, ArrayList<Mood> moodsList){
        String      moodName;
        LocalDate   date;
        LocalTime   time;
        int         index;

        System.out.println("Enter the NAME of the mood you want to edit the note of");
        moodName = input.nextLine();
        System.out.println("Enter the DATE, in MM/dd/yyyy format, of the mood you want to edit the note of");
        date = LocalDate.parse(input.nextLine());
        System.out.println("Enter the TIME, in HH:mm:ss format, of the mood you want to edit the note of");
        time = LocalTime.parse(input.nextLine());

        index = moodsList.indexOf(new Mood(moodName, date, time));
        if (index == -1) throw new InvalidMoodException("The Mood hasn't been found for editing");

        System.out.println("Mood found! here's the current selected mood:\n\t" + moodsList.get(index).toString());
        System.out.println("Enter the new note for the mood");
        moodsList.get(index).setNotes(input.nextLine());
    }
    private static void searchMoods(Scanner input, ArrayList<Mood> moodsList) {
        System.out.println("Search by:\n1. Name\n2. Date\n(Enter \"1\" or \"2\")");
        String choice = input.nextLine();

        switch (choice) {
            case "1":
                System.out.println("Enter the mood name to search for:");
                String searchName = input.nextLine();
                boolean foundName = false;
                for (Mood mood : moodsList) {
                    if (mood.getName().equalsIgnoreCase(searchName)) {
                        System.out.println(mood);
                        foundName = true;
                    }
                }
                if (!foundName) {
                    System.out.println("No moods found with that name.");
                }
                break;

            case "2":
                System.out.println("Enter the date to search for (MM/dd/yyyy):");
                LocalDate searchDate = LocalDate.parse(input.nextLine());
                boolean foundDate = false;
                for (Mood mood : moodsList) {
                    if (mood.getDate().equals(searchDate)) {
                        System.out.println(mood);
                        foundDate = true;
                    }
                }
                if (!foundDate) {
                    System.out.println("No moods found on that date.");
                }
                break;

            default:
                throw new InvalidMoodException("Invalid choice. Please enter 1 or 2.");
        }
    }

    private static void getAllMoods(ArrayList<Mood> moodsList) {
        if (moodsList.isEmpty()) {
            System.out.println("No moods have been recorded yet.");
            return;
        }

        System.out.println("All recorded moods:");
        for (Mood mood : moodsList) {
            System.out.println(mood);
        }
    }

    private static void writeMoodsToFile(ArrayList<Mood> moodsList) {
        if (moodsList.isEmpty()) {
            System.out.println("No moods to write to file.");
            return;
        }

        try (FileWriter writer = new FileWriter("moods.txt")) {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            for (Mood mood : moodsList) {
                String moodEntry = String.format("Mood: %s, Date: %s, Time: %s, Notes: %s%n",
                        mood.getName(),
                        mood.getDate().format(dateFormatter),
                        mood.getTime().format(timeFormatter),
                        mood.getNotes() != null ? mood.getNotes() : "No notes"
                );
                writer.write(moodEntry);
            }
            System.out.println("Successfully wrote moods to moods.txt");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

}
