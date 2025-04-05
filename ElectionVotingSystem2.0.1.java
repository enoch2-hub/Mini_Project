import java.util.Scanner;

public class ElectionVotingSystem {
    static String[] candidates; // Array to hold candidate names
    static String[] voterIds; // Array to hold voter IDs
    static int[] votes; // Array to hold votes for each candidate
    static boolean[] voted; // Array to track whether each voter has voted
    static Scanner scanner = new Scanner(System.in);
    static final String ADMIN_KEY = "admin123"; // Admin key for accessing the admin panel
    static boolean running = true; // Flag to control the application loop
    static boolean electionSetupComplete = false; // Flag to track if admin setup is done

    public static void main(String[] args) {
        System.out.println("Welcome to Election Voting System");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Admin Panel");
            System.out.println("2. Voter Panel");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    if (adminPanel()) {
                        electionSetupComplete = true;
                        System.out.println("Election setup completed by the admin.");
                    }
                    break;
                case 2:
                    if (electionSetupComplete) {
                        voterPanel();
                        running = false; // Exit after voter panel is done
                    } else {
                        System.out.println("Election setup not completed by the admin. Please select Admin Panel first.");
                    }
                    break;
                case 3:
                    System.out.println("Exiting the application.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
        scanner.close();
    }

    static boolean adminPanel() {
        System.out.print("Enter Admin Key: ");
        String adminKey = scanner.next();

        if (adminKey.equals(ADMIN_KEY)) {
            System.out.println("Admin Panel");

            System.out.print("Enter number of candidates: ");
            int numCandidates = scanner.nextInt();
            candidates = new String[numCandidates];
            votes = new int[numCandidates];
            System.out.println("Enter candidate names (letters only, no numbers):");

            scanner.nextLine(); // Clear the buffer

            for (int i = 0; i < numCandidates; i++) {
                while (true) {
                    System.out.print("Candidate " + (i + 1) + ": ");
                    String name = scanner.nextLine().trim();

                    // Check if name contains only letters (no numbers)
                    if (name.matches("[a-zA-Z ]+")) {
                        candidates[i] = name;
                        break;
                    } else {
                        System.out.println("Invalid name! Use letters only (e.g., 'John Doe').");
                    }
                }
            }

            System.out.print("Enter number of voters: ");
            int numVoters = scanner.nextInt();
            voterIds = new String[numVoters];
            voted = new boolean[numVoters];

            System.out.println("Enter voter IDs:");
            for (int i = 0; i < numVoters; i++) {
                System.out.print("Voter ID " + (i + 1) + ": ");
                voterIds[i] = scanner.next();
            }

            System.out.println("Admin Panel finished.");
            return true;
        } else {
            System.out.println("Invalid Admin Key.");
            return false;
        }
    }

    static void voterPanel() {
        System.out.println("\nVoter Panel");
        if (!electionSetupComplete || candidates == null || voterIds == null) {
            System.out.println("Election setup not completed by the admin. Returning to the main menu.");
            return;
        }

        int votersVoted = 0;
        while (votersVoted < voterIds.length && running) {
            System.out.print("Enter your voter ID to vote (or type 'exit' to leave): ");
            String voterId = scanner.next();

            if (voterId.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the voting system.");
                break;
            }

            int voterIndex = findVoter(voterId);

            if (voterIndex != -1 && !voted[voterIndex]) {
                System.out.println("You are eligible to vote.");
                displayCandidates();
                System.out.print("Enter the number of your chosen candidate: ");
                int candidateNumber = scanner.nextInt() - 1;

                if (candidateNumber >= 0 && candidateNumber < candidates.length) {
                    votes[candidateNumber]++;
                    voted[voterIndex] = true;
                    System.out.println("Vote confirmed.");
                    votersVoted++;
                } else {
                    System.out.println("Invalid candidate number.");
                }
            } else if (voterIndex != -1 && voted[voterIndex]) {
                System.out.println("You have already voted.");
            } else {
                System.out.println("You are not eligible to vote.");
            }
        }

        System.out.println("\nVoting ended.");
        running = false; // Exit after voting is done
        displayResults(); // Display results after voting concludes
    }

    static int findVoter(String voterId) {
        for (int i = 0; i < voterIds.length; i++) {
            if (voterIds[i].equals(voterId)) {
                return i;
            }
        }
        return -1; // Return -1 if the voter ID is not found
    }

    static void displayCandidates() {
        if (candidates != null) {
            System.out.println("Available candidates:");
            for (int i = 0; i < candidates.length; i++) {
                System.out.println((i + 1) + ". " + candidates[i]);
            }
        } else {
            System.out.println("No candidates have been added yet.");
        }
    }

    static void displayResults() {
        if (electionSetupComplete && candidates != null && voterIds != null) {
            System.out.println("\nElection Results:");
            for (int i = 0; i < candidates.length; i++) {
                double percentage = (voterIds.length > 0) ? ((double) votes[i] / voterIds.length) * 100 : 0;
                System.out.printf("%s: %d votes (%.2f%%)\n", candidates[i], votes[i], percentage);
            }

            int maxVotes = 0;
            String winner = "";
            for (int i = 0; i < candidates.length; i++) {
                if (votes[i] > maxVotes) {
                    maxVotes = votes[i];
                    winner = candidates[i];
                }
            }

            System.out.println("\nThe winner is: " + winner);
        } else if (!electionSetupComplete) {
            System.out.println("Election results cannot be displayed as the election setup is incomplete.");
        }
    }
}