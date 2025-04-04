# Mini-Project

import java.util.Scanner;

public class SimpleVotingSystem {
    // Arrays to store candidate names and their votes
    private static String[] candidates = new String[10];
    private static int[] votes = new int[10];
    private static int candidateCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Initialize with some candidates
        addCandidate("Alice");
        addCandidate("Bob");
        addCandidate("Charlie");

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    displayCandidates();
                    break;
                case 2:
                    castVote();
                    break;
                case 3:
                    displayResults();
                    break;
                case 4:
                    addNewCandidate();
                    break;
                case 5:
                    resetVotes();
                    break;
                case 6:
                    findWinner();
                    break;
                case 7:
                    running = false;
                    System.out.println("Exiting voting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // Function 1: Display menu
    private static void displayMenu() {
        System.out.println("\n=== Simple Voting System ===");
        System.out.println("1. Display Candidates");
        System.out.println("2. Cast a Vote");
        System.out.println("3. Display Results");
        System.out.println("4. Add New Candidate");
        System.out.println("5. Reset All Votes");
        System.out.println("6. Find Winner");
        System.out.println("7. Exit");
    }

    // Function 2: Get integer input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a number!");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // Function 3: Display all candidates
    private static void displayCandidates() {
        System.out.println("\n=== Candidates ===");
        for (int i = 0; i < candidateCount; i++) {
            System.out.println((i+1) + ". " + candidates[i]);
        }
    }

    // Function 4: Cast a vote
    private static void castVote() {
        displayCandidates();
        if (candidateCount == 0) {
            System.out.println("No candidates available!");
            return;
        }

        int choice = getIntInput("Enter candidate number to vote for: ");
        if (choice > 0 && choice <= candidateCount) {
            votes[choice-1]++;
            System.out.println("Vote cast for " + candidates[choice-1]);
        } else {
            System.out.println("Invalid candidate number!");
        }
    }

    // Function 5: Display voting results
    private static void displayResults() {
        System.out.println("\n=== Voting Results ===");
        for (int i = 0; i < candidateCount; i++) {
            System.out.println(candidates[i] + ": " + votes[i] + " votes");
        }
    }

    // Function 6: Add new candidate
    private static void addCandidate(String name) {
        if (candidateCount < candidates.length) {
            candidates[candidateCount] = name;
            votes[candidateCount] = 0;
            candidateCount++;
        }
    }

    private static void addNewCandidate() {
        if (candidateCount >= candidates.length) {
            System.out.println("Maximum candidates reached!");
            return;
        }
        System.out.print("Enter new candidate name: ");
        scanner.nextLine(); // Clear buffer
        String name = scanner.nextLine();
        addCandidate(name);
        System.out.println("Candidate added: " + name);
    }

    // Function 7: Reset all votes
    private static void resetVotes() {
        for (int i = 0; i < candidateCount; i++) {
            votes[i] = 0;
        }
        System.out.println("All votes have been reset.");
    }

    // Bonus function: Find winner
    private static void findWinner() {
        if (candidateCount == 0) {
            System.out.println("No candidates available!");
            return;
        }

        int maxVotes = votes[0];
        String winner = candidates[0];
        boolean tie = false;

        for (int i = 1; i < candidateCount; i++) {
            if (votes[i] > maxVotes) {
                maxVotes = votes[i];
                winner = candidates[i];
                tie = false;
            } else if (votes[i] == maxVotes) {
                tie = true;
            }
        }

        if (tie) {
            System.out.println("There's a tie between multiple candidates!");
        } else {
            System.out.println("Current winner: " + winner + " with " + maxVotes + " votes");
        }
    }
}
