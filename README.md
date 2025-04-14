# Election Voting System - Mini Project

![Image](https://github.com/user-attachments/assets/12601e38-c815-46be-bd19-96854d2e9750)


## Overview

This is a simple console-based Election Voting System developed as a mini-project. It allows an administrator to set up candidates and register voters, and then enables registered voters to cast their votes. Finally, it displays the election results, including the total votes for each candidate, their vote percentage, and the winner(s).

This project was developed as an individual/group contribution for the Fundamentals of Programming course (Level 3).

## Features

* **Admin Panel:**
    * Secure access using an admin key.
    * Ability to define the number of candidates and their names.
    * Ability to register the number of voters and their unique IDs (with duplicate ID checking).
* **Voter Panel:**
    * Allows registered voters to cast their vote by entering their unique ID.
    * Prevents unregistered voters from voting.
    * Ensures each voter can only vote once.
    * Displays the list of available candidates with their corresponding numbers.
* **Results Display:**
    * Shows the total number of votes received by each candidate.
    * Calculates and displays the percentage of votes for each candidate.
    * Identifies and displays the winner(s), handling ties correctly (displays "Winners" in case of a tie).
* **Simple Console Interface:** User interaction is through basic text-based prompts and input.

## How to Run

1.  **Save the code:** Save the provided Java code as `ElectionVotingSystem.java`.
2.  **Compile:** Open a terminal or command prompt, navigate to the directory where you saved the file, and compile the code using the Java compiler:
    ```bash
    javac ElectionVotingSystem.java
    ```
3.  **Run:** After successful compilation, run the application using the Java Virtual Machine:
    ```bash
    java ElectionVotingSystem
    ```
4.  **Follow the prompts:** The application will guide you through the admin setup, voter login, voting process, and results display.

## Code Structure

The project consists of a single Java file:

* **`ElectionVotingSystem.java`:** Contains the entire codebase, including:
    * `main` method: Entry point of the application and handles the main menu.
    * `adminPanel()`: Manages the administrator functionalities for setting up the election.
    * `voterpanel()`: Handles the voter interface for casting votes.
    * `findvoter()`: Helper function to locate a voter by ID.
    * `display_Candidates()`: Displays the list of candidates.
    * `displayresults()`: Calculates and displays the election results.
    * `isVoter_IdUnique()`: Helper function to check for unique voter IDs.
      

## Future Enhancements (Optional)

* Implement data persistence using files or a database.
* Enhance security with password hashing and more robust authentication.
* Develop a graphical user interface (GUI) for better usability.
* Add support for different voting methods (e.g., ranked-choice).
* Implement features like vote auditing and reporting.
