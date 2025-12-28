import java.util.ArrayList;
import java.util.Scanner;

class habit {
    String Name;
    boolean completed;

    habit(String Name) {
        this.Name = Name;
        this.completed = false;
    }
}

public class DailyHabitTracker {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            ArrayList<habit> habits = new ArrayList<>();

            habits.add(new habit("Morning Yoga"));
            habits.add(new habit("Drinking Enough Water"));
            habits.add(new habit("Coding Practice"));
            habits.add(new habit("Checking Emails"));
            habits.add(new habit("Planning the Day Before"));

            System.out.println("----- DAILY HABIT TRACKER -----");

            for (habit h : habits) {
                System.out.println("Did you complete " + h.Name + "? (yes/no)");
                String input = sc.nextLine();
                h.completed = input.equalsIgnoreCase("yes");
            }

            System.out.println("\n----- SUMMARY -----");
            int completedCount = 0;

            for (habit h : habits) {
                System.out.println(h.Name + " : " + (h.completed ? "Completed" : "Pending"));
                if (h.completed) completedCount++;
            }

            System.out.println("\nHabit Score: " + completedCount + "/" + habits.size());
        }
    }
}