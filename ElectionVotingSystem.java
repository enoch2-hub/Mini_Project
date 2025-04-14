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







//~Heshan Contribution - Start
//enter candidates name.candidates names are letters only
     System.out.println("Enter candidate names: ");
        for (int X = 0; X < numCandidates; X++) {
            while (true) {
                System.out.print("Candidate " + (X + 1) + ": ");
                String name = scanner.nextLine().trim();
                if (name.matches("[a-zA-Z ]+")) {
                    candidates[X] = name;
                    break;
                }
                System.out.println("error! Use letters only");
            }
     }

//~Heshan Contribution - End





    
    
// Enoch's Contribution - (Start)-----------
    
//displayresults()` function, Implemented the logic to determine and display the winner(s)
//handling ties and the singular/plural form of "winner/winners".
//functionality to handle the cases where no voters are registered, or no votes have been cast.

    static void displayresults() {
        System.out.println("\n=== ELECTION RESULTS ===");

        if (voterIds == null || voterIds.length == 0) {
            System.out.println("No voters registered!");
            return;
        }

        int totalVotes = 0;
        for (int vote : votes) totalVotes += vote;

        if (totalVotes == 0) {
            System.out.println("No votes cast yet!");
            return;
        }

        for (int X = 0; X < candidates.length; X++) {
            double percentage = (votes[X] * 100.0) / totalVotes;
            String voteString = (votes[X] == 1) ? "vote" : "votes";
            System.out.printf("\n%s: %d %s (%.2f%%)\n",
                    candidates[X], votes[X], voteString, percentage);
        }

        int maxVotes = -1;
        StringBuilder winners = new StringBuilder();

        for (int win = 0; win < candidates.length; win++) {
            if (votes[win] > maxVotes) {
                maxVotes = votes[win];
                winners = new StringBuilder(candidates[win]);
            } else if (votes[win] == maxVotes) {
                if (winners.length() > 0) {
                    winners.append(", ");
                }
                winners.append(candidates[win]);
            }
        }

        if (winners.indexOf(",") > 0) {
            System.out.println("\nWinners : " + winners);
        } else {
            System.out.println("\nWinner : " + winners);
        }

    }
}


// Enoch's Contribution - (End)------------






    
