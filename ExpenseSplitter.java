import java.util.ArrayList;
import java.util.Scanner;

class User {
    String name;
    double balance = 0;

    User(String name) {
        this.name = name;
    }
}

public class ExpenseSplitter {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<User> users = new ArrayList<>();

        System.out.print("Enter number of users: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter user name: ");
            users.add(new User(sc.nextLine()));
        }

        System.out.print("Enter total expense amount: ");
        double amount = sc.nextDouble();

        double splitAmount = amount / users.size();

        for (User u : users) {
            u.balance -= splitAmount;
        }

        System.out.print("Who paid the bill? (enter user index starting from 1): ");
        int payerIndex = sc.nextInt() - 1;
        users.get(payerIndex).balance += amount;

        System.out.println("\nPayment Summary:");
        for (User u : users) {
            System.out.println(u.name + " balance: " + u.balance);
        }
    }

}
