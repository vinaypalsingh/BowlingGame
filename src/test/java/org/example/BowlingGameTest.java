package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingGameTest {

    @Test
    public void testGutterGame() {
        BowlingGameSolution game = new BowlingGameSolution();
        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }
        assertEquals(0, game.score());
    }

    @Test
    public void testAllOnes() {
        BowlingGameSolution game = new BowlingGameSolution();
        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }
        assertEquals(20, game.score());
    }

    @Test
    public void testSpareFollowedByThree() {
        BowlingGameSolution game = new BowlingGameSolution();
        game.roll(5);
        game.roll(5); // Spare
        game.roll(3);
        for (int i = 0; i < 17; i++) {
            game.roll(0);
        }
        assertEquals(16, game.score());
    }

    @Test
    public void testStrikeFollowedByThreeAndFour() {
        BowlingGameSolution game = new BowlingGameSolution();
        game.roll(10); // Strike
        game.roll(3);
        game.roll(4);
        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }
        assertEquals(24, game.score());
    }

    @Test
    public void testPerfectGame() {
        BowlingGameSolution game = new BowlingGameSolution();
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }
        assertEquals(300, game.score());
    }
}
