Bowling Game Scorer
This is a simple Java program that calculates the score of a 10-frame bowling game based on the given rules. The program uses JUnit tests to ensure accuracy and correctness.

/src
  /main
    /java
      BowlingGame.java
  /test
    /java
      BowlingGameTest.java
pom.xml
README.md

BowlingGame.java — Contains the logic to calculate the score of a bowling game.
BowlingGameTest.java — JUnit tests covering various scoring scenarios.
pom.xml — Maven configuration file.
README.md — Project documentation.

Rules of Bowling Scoring
* A bowling game has 10 frames.
* Each frame allows two rolls to knock down 10 pins in total.
* Spare: If you knock down all 10 pins in two rolls, the score for that frame is 10 + the number of pins knocked down in the next roll.
* Strike: If you knock down all 10 pins in the first roll, the score is 10 + the sum of the next two rolls.
* In the 10th frame, if you roll a strike or spare, you get up to 3 rolls.

Test Cases Covered
The following scenarios are tested: 
* Gutter game (all rolls score 0) — expected score: 0
* All ones (each roll scores 1) — expected score: 20
* Single spare — expected score calculated with bonus roll
* Single strike — expected score calculated with two bonus rolls
* Perfect game (12 strikes) — expected score: 300



