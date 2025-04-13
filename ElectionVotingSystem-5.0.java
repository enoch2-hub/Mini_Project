import java.util.Scanner;

public class ElectionVotingSystem {
    static String[] candidates; // Array to hold candidate names
    static int[] voterIds; // Array to hold voter IDs
    static int[] votes; // Array to hold votes for each candidate
    static boolean[] voted; // Array to track whether each voter has voted
    static Scanner scanner = new Scanner(System.in);
    static final String ADMIN_KEY = "admin123"; // Admin key for accessing the admin panel

    public static void main(String[] args) {
        System.out.println("\n$$$$$ Welcome To The Voting System $$$$$");

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


            /* adding main panel*/

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
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again");
            }
        } while (choice != 4);
    }


    //Admin Panel========================

    // enter admin key. admin key is admin123

    static boolean adminPanel() {
        System.out.print("\nEnter Admin Key: ");
        String adminKey = scanner.nextLine();

        if (!adminKey.equals(ADMIN_KEY)) {
            System.out.println("Invalid Admin Key");
            return false;
        }


        //enter number of candidates

        System.out.println("\n=== ADMIN PANEL ===");
        System.out.print("Enter number of candidates: ");
        int numCandidates = scanner.nextInt();
        scanner.nextLine();

        candidates = new String[numCandidates];
        votes = new int[numCandidates];


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


        /* enter  number of voters. voter id are numbers only and don't use same voter id */

        System.out.print("\nEnter number of voters: ");
        int numVoters = scanner.nextInt();
        scanner.nextLine();
        voterIds = new int[numVoters];
        voted = new boolean[numVoters];

        System.out.println("Enter voter IDs: ");
        for (int vot = 0; vot < numVoters; vot++) {
            while (true) {
                try {
                    System.out.print("Voter ID " + (vot + 1) + ": ");
                    int id = Integer.parseInt(scanner.nextLine());

                    if (isVoter_IdUnique(id, vot)) {
                        voterIds[vot] = id;
                        break;
                    } else {
                        System.out.println("This voter ID already exists! Please enter a unique ID");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter numbers only");
                }
            }
        }

        System.out.println("\n ");
        return true;
    }

    static boolean isVoter_IdUnique(int id, int currentIndex) {
        for (int che = 0; che < currentIndex; che++) {
            if (voterIds[che] == id) {
                return false;
            }
        }
        return true;
    }


    //Functions related to the Voter Panel

    static int findvoter(int voterId) {
        for (int find = 0; find < voterIds.length; find++) {
            if (voterIds[find] == voterId) {
                return find;
            }
        }
        return -1;
    }

    static void display_Candidates() {
        System.out.println("\nAvailable Candidates:");
        for (int dis = 0; dis < candidates.length; dis++) {
            System.out.println((dis + 1) + ". " + candidates[dis]);
        }
    }

    //Voter Panel========================

    static void voterpanel() {
        System.out.println("\n=== VOTER PANEL ===");
        int votersVoted = 0;

        System.out.println("enter '0' exit to main menu"); //do you want go to main menu enter number 0

        while (votersVoted < voterIds.length) {
            System.out.print("\nEnter voter ID : ");
            String input = scanner.nextLine();

            if (input.equals("0")) break;

            try {
                int voterId = Integer.parseInt(input);
                int voterIndex = findvoter(voterId);

                if (voterIndex == -1) {
                    System.out.println("You are not eligible to vote");
                    continue;
                }

                if (voted[voterIndex]) {
                    System.out.println("You have already voted");
                    continue;
                }

                System.out.println("You are eligible to vote");
                display_Candidates();

                System.out.print("Enter candidate number: ");
                try {
                    int candidateNum = Integer.parseInt(scanner.nextLine()) - 1;
                    if (candidateNum >= 0 && candidateNum < candidates.length) {
                        votes[candidateNum]++;
                        voted[voterIndex] = true;
                        votersVoted++;
                        System.out.println("Vote recorded for " + candidates[candidateNum]);
                    } else {
                        System.out.println("Invalid candidate number");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid voter ID");
            }
        }

        System.out.println("\nVoting system ended");
    }


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

//        int maxVotes = -1;
//        StringBuilder winners = new StringBuilder();
//
//        for (int win = 0; win < candidates.length; win++) {
//            if (votes[win] > maxVotes) {
//                maxVotes = votes[win];
//                winners = new StringBuilder(candidates[win]);
//            } else if (votes[win] == maxVotes) {
//                winners.append(", ").append(candidates[win]);
//            }
//
//            System.out.println("\nWinner : " + winners.toString());
//        }

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
