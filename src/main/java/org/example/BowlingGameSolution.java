package org.example;

public class BowlingGameSolution {
    private final int[] rolls = new int[21]; // Store rolls (max 21 rolls in a game)
    private int currentRoll = 0; // Track the current roll

    // Record a roll
    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    // Calculate the final score
    public int score() {
        int score = 0;
        int rollIndex = 0;

        // Iterate over 10 frames
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(rollIndex)) { // Strike
                score += 10 + strikeBonus(rollIndex);
                rollIndex++;
            } else if (isSpare(rollIndex)) { // Spare
                score += 10 + spareBonus(rollIndex);
                rollIndex += 2;
            } else { // Normal frame
                score += sumOfBallsInFrame(rollIndex);
                rollIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int rollIndex) {
        return rolls[rollIndex] == 10;
    }

    private boolean isSpare(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1] == 10;
    }

    private int strikeBonus(int rollIndex) {
        return rolls[rollIndex + 1] + rolls[rollIndex + 2];
    }

    private int spareBonus(int rollIndex) {
        return rolls[rollIndex + 2];
    }

    private int sumOfBallsInFrame(int rollIndex) {
        return rolls[rollIndex] + rolls[rollIndex + 1];
    }

    public static void main(String[] args) {
        BowlingGameSolution game = new BowlingGameSolution();
        for (int i = 0; i < 12; i++) {
            game.roll(10); // Perfect game (12 strikes)
        }
        System.out.println("Final Score: " + game.score()); // Expected: 300
    }
}
