// Udara contribution part start
// the main menu and system setup, including the variable and initial welcome message and the main loop for user interaction
import java.util.Scanner;
public class ElectionVotingSystem {
    static String[] candidates;
    static int[] voterIds;
    static int[] votes;
    static boolean[] voted;
    static Scanner scanner = new Scanner(System.in);
    static final String ADMIN_KEY = "admin123";

    public static void main(String[] args) {
        System.out.println("\n$$$$$ Welcome to Voting System $$$$$");

        int choice;
        do {
            System.out.println("\n***** Main Menu *****");
            System.out.println("1. Admin Panel");
            System.out.println("2. Voter Panel");
            System.out.println("3. View Results");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (adminPanel()) {
                        System.out.println("Admin setup completed successfully");
                    } else {
                        System.out.println("Admin setup failed. Try again");
                    }
                    break;
                case 2:
                    if (candidates == null || voterIds == null) {
                        System.out.println("Please setup candidates and voters first  Admin Panel");
                    } else {
                        voterpanel();
                    }
                    break;
                case 3:
                    if (candidates == null) {
                        System.out.println("No election data available. Setup data first");
                    } else {
                        displayresults();
                    }
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 4);
    }
// Udara Contribution end 
