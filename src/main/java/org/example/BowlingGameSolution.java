package org.example;

import java.util.Scanner;

public class BowlingGameSolution {

    // Array to store the number of pins knocked down in each roll (max 21 rolls in a full game)
    private final int[] pinsKnockedDown = new int[21];

    // Keeps track of the current roll index (which roll we're on)
    private int rollNumber = 0;

    // Method to record the number of pins knocked down for each roll
    public void roll(int pins) {
        pinsKnockedDown[rollNumber] = pins; // Store the pins knocked down for this roll
        rollNumber = rollNumber + 1; // Move to the next roll
    }

    // Method to calculate the total score of the game after all frames are played
    public int calculateTotalScore() {
        int totalScore = 0; // Start the total score at 0
        int currentRollIndex = 0; // Start at the first roll

        // A bowling game always has 10 frames
        for (int frameNumber = 1; frameNumber <= 10; frameNumber = frameNumber + 1) {

            // Check if the player got a strike (knocked down all 10 pins in the first roll of the frame)
            if (isStrike(currentRollIndex)) {
                totalScore = totalScore + 10 + pinsKnockedDown[currentRollIndex + 1] + pinsKnockedDown[currentRollIndex + 2];
                currentRollIndex = currentRollIndex + 1; // Move to the next roll (strike skips the second roll)

                // Check if the player got a spare (knocked down 10 pins across both rolls in a frame)
            } else if (isSpare(currentRollIndex)) {
                totalScore = totalScore + 10 + pinsKnockedDown[currentRollIndex + 2];
                currentRollIndex = currentRollIndex + 2; // Move to the next frame (after both rolls)

                // If it's an open frame (less than 10 pins knocked down in two rolls)
            } else {
                totalScore = totalScore + pinsKnockedDown[currentRollIndex] + pinsKnockedDown[currentRollIndex + 1];
                currentRollIndex = currentRollIndex + 2; // Move to the next frame
            }
        }
        return totalScore; // Return the final score after all frames are calculated
    }

    // Checks if the current roll was a strike (10 pins knocked down in one roll)
    private boolean isStrike(int currentRollIndex) {
        return pinsKnockedDown[currentRollIndex] == 10;
    }

    // Checks if the current frame was a spare (10 pins knocked down in two rolls)
    private boolean isSpare(int currentRollIndex) {
        return pinsKnockedDown[currentRollIndex] + pinsKnockedDown[currentRollIndex + 1] == 10;
    }

    // Main method to run the game from the console
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BowlingGameSolution game = new BowlingGameSolution();

        System.out.println("🎳 Welcome to the Bowling Score Calculator!");
        System.out.println("Enter the number of pins knocked down for each roll:");

        // Loop through 10 frames (with extra rolls for the 10th frame if needed)
        for (int frame = 1; frame <= 10; frame = frame + 1) {
            System.out.println("Frame " + frame + ":");

            // First roll of the frame
            System.out.print("  Roll 1: ");
            int firstRoll = scanner.nextInt();
            game.roll(firstRoll);

            if (firstRoll == 10 && frame < 10) { // If strike, skip second roll (unless it's the 10th frame)
                continue;
            }

            // Second roll of the frame
            System.out.print("  Roll 2: ");
            int secondRoll = scanner.nextInt();
            game.roll(secondRoll);

            // If it's the 10th frame and there's a spare or strike, allow a bonus roll
            if (frame == 10 && (firstRoll == 10 || firstRoll + secondRoll == 10)) {
                System.out.print("  Bonus roll: ");
                int bonusRoll = scanner.nextInt();
                game.roll(bonusRoll);
            }
        }

        // Calculate and display final score
        int finalScore = game.calculateTotalScore();
        System.out.println("Final score: " + finalScore);
    }
}
