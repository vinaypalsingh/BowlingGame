package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    private BowlingGameSolution game;

    // Sets up a new BowlingGame instance before each test
    @BeforeEach
    public void setUp() {
        game = new BowlingGameSolution();
    }

    // Helper method to roll a ball multiple times with the same pin count
    private void rollMultipleTimes(int rolls, int pins) {
        for (int i = 0; i < rolls; i = i + 1) {
            game.roll(pins);
        }
    }

    // Test for a "gutter game" — where all rolls knock down 0 pins
    @Test
    public void testGutterGame() {
        rollMultipleTimes(20, 0); // 20 rolls, all with 0 pins
        assertEquals(0, game.calculateTotalScore(), "Score should be 0 for a gutter game.");
    }

    // Test for a game where all rolls knock down 1 pin
    @Test
    public void testAllOnesGame() {
        rollMultipleTimes(20, 1); // 20 rolls, all with 1 pin knocked down
        assertEquals(20, game.calculateTotalScore(), "Score should be 20 when all rolls knock down 1 pin.");
    }

    // Test for a single spare followed by a 3 (should score 16)
    @Test
    public void testSingleSpare() {
        game.roll(5); // First roll: 5 pins
        game.roll(5); // Second roll: 5 pins (makes a spare)
        game.roll(3); // Bonus roll: 3 pins
        rollMultipleTimes(17, 0); // Remaining rolls: 0 pins
        assertEquals(16, game.calculateTotalScore(), "Score should be 16 for a spare followed by a 3.");
    }

    // Test for a single strike followed by a 3 and a 4 (should score 24)
    @Test
    public void testSingleStrike() {
        game.roll(10); // Strike: all 10 pins in the first roll of a frame
        game.roll(3); // Next frame roll: 3 pins
        game.roll(4); // Next roll: 4 pins
        rollMultipleTimes(16, 0); // Remaining rolls: 0 pins
        assertEquals(24, game.calculateTotalScore(), "Score should be 24 for a strike followed by a 3 and a 4.");
    }

    // Test for a perfect game (12 rolls, all strikes, max score 300)
    @Test
    public void testPerfectGame() {
        rollMultipleTimes(12, 10); // 12 strikes (10 pins each roll)
        assertEquals(300, game.calculateTotalScore(), "Score should be 300 for a perfect game.");
    }

    // Test for a game with alternating strikes and spares
    @Test
    public void testAlternatingStrikesAndSpares() {
        for (int i = 0; i < 5; i = i + 1) {
            game.roll(10); // Strike
            game.roll(5); // Spare: 5 + 5 = 10
            game.roll(5);
        }
        game.roll(10); // Extra roll for the 10th frame
        assertEquals(200, game.calculateTotalScore(), "Score should be 200 for alternating strikes and spares.");
    }

    // Test for a final frame spare with a bonus roll
    @Test
    public void testFinalFrameSpare() {
        rollMultipleTimes(18, 0); // First 9 frames: all 0s
        game.roll(5); // 10th frame first roll: 5 pins
        game.roll(5); // 10th frame second roll: spare
        game.roll(3); // Bonus roll: 3 pins
        assertEquals(13, game.calculateTotalScore(), "Score should be 13 for a spare in the final frame followed by a 3.");
    }

    // Test for a final frame strike with bonus rolls
    @Test
    public void testFinalFrameStrike() {
        rollMultipleTimes(18, 0); // First 9 frames: all 0s
        game.roll(10); // Strike in the final frame
        game.roll(3); // Bonus roll 1
        game.roll(4); // Bonus roll 2
        assertEquals(17, game.calculateTotalScore(), "Score should be 17 for a strike in the final frame followed by a 3 and a 4.");
    }
}
