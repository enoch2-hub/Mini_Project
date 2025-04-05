import java.util.Scanner;

public class ElectionVotingSystem {
    static String[] candidates;
    static String[] voterIds;
    static int[] votes;
    static boolean[] voted;
    static Scanner scanner = new Scanner(System.in);
    static final String ADMIN_KEY = "admin123";

    public static void main(String[] args) {
        System.out.println("Welcome to Election Voting System");

        int choice;
        do {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. Admin Panel");
            System.out.println("2. Voter Panel");
            System.out.println("3. View Results");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear buffer

            switch (choice) {
                case 1:
                    if (adminPanel()) {
                        System.out.println("Admin setup completed successfully.");
                    } else {
                        System.out.println("Admin setup failed. Try again.");
                    }
                    break;
                case 2:
                    if (candidates == null || voterIds == null) {
                        System.out.println("Please setup candidates and voters first via Admin Panel!");
                    } else {
                        voterPanel();
                    }
                    break;
                case 3:
                    if (candidates == null) {
                        System.out.println("No election data available. Setup election first.");
                    } else {
                        displayResults();
                    }
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    static boolean adminPanel() {
        System.out.print("\nEnter Admin Key: ");
        String adminKey = scanner.nextLine();

        if (!adminKey.equals(ADMIN_KEY)) {
            System.out.println("Invalid Admin Key!");
            return false;
        }

        System.out.println("\n=== ADMIN PANEL ===");
        System.out.print("Enter number of candidates: ");
        int numCandidates = scanner.nextInt();
        scanner.nextLine(); // Clear buffer

        candidates = new String[numCandidates];
        votes = new int[numCandidates];

        System.out.println("Enter candidate names (letters only):");
        for (int i = 0; i < numCandidates; i++) {
            while (true) {
                System.out.print("Candidate " + (i + 1) + ": ");
                String name = scanner.nextLine().trim();
                if (name.matches("[a-zA-Z ]+")) {
                    candidates[i] = name;
                    break;
                }
                System.out.println("Invalid name! Use letters only.");
            }
        }

        System.out.print("\nEnter number of voters: ");
        int numVoters = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        voterIds = new String[numVoters];
        voted = new boolean[numVoters];

        System.out.println("Enter voter IDs:");
        for (int i = 0; i < numVoters; i++) {
            System.out.print("Voter ID " + (i + 1) + ": ");
            voterIds[i] = scanner.nextLine();
        }

        System.out.println("\nAdmin setup completed.");
        return true;
    }

    static void voterPanel() {
        System.out.println("\n=== VOTER PANEL ===");
        int votersVoted = 0;

        while (votersVoted < voterIds.length) {
            System.out.print("\nEnter voter ID (or 'exit' to end): ");
            String voterId = scanner.nextLine();

            if (voterId.equalsIgnoreCase("exit")) break;

            int voterIndex = findVoter(voterId);

            if (voterIndex == -1) {
                System.out.println("Invalid voter ID!");
                continue;
            }

            if (voted[voterIndex]) {
                System.out.println("You have already voted.");
                continue;
            }

            System.out.println("You are eligible to vote.");
            displayCandidates();

            System.out.print("Enter candidate number: ");
            try {
                int candidateNum = Integer.parseInt(scanner.nextLine()) - 1;
                if (candidateNum >= 0 && candidateNum < candidates.length) {
                    votes[candidateNum]++;
                    voted[voterIndex] = true;
                    votersVoted++;
                    System.out.println("Vote recorded for " + candidates[candidateNum]);
                } else {
                    System.out.println("Invalid candidate number!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }

        System.out.println("\nVoting session ended.");
    }

    static int findVoter(String voterId) {
        for (int i = 0; i < voterIds.length; i++) {
            if (voterIds[i].equals(voterId)) {
                return i;
            }
        }
        return -1;
    }

    static void displayCandidates() {
        System.out.println("\nAvailable Candidates:");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println((i + 1) + ". " + candidates[i]);
        }
    }

    static void displayResults() {
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

        // Display individual results
        for (int i = 0; i < candidates.length; i++) {
            double percentage = (votes[i] * 100.0) / totalVotes;
            System.out.printf("%s: %d votes (%.2f%%)\n",
                    candidates[i], votes[i], percentage);
        }

        // Determine winner(s)
        int maxVotes = -1;
        StringBuilder winners = new StringBuilder();

        for (int i = 0; i < candidates.length; i++) {
            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
                winners = new StringBuilder(candidates[i]);
            } else if (votes[i] == maxVotes) {
                winners.append(", ").append(candidates[i]);
            }
        }

        System.out.println("\nWinner(s): " + winners.toString());
    }
}
