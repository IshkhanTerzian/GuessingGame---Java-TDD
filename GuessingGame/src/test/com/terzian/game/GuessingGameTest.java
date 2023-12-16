package com.terzian.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GuessingGameTest {

    public static final int GAME_RANDOMNESS_RETRIES = 100;
    private GuessingGame game;

    @BeforeEach
    void setUp() {
        game = new GuessingGame();
    }



    @Test
    public void testSimpleWinSituation() {
        int winningGuess = game.getRandomNumber();
        System.out.println(winningGuess);
        String message = game.guess(winningGuess);
        assertEquals("You got it in 1 try", message);
    }

    @Test
    public void testOneWrongNegGuessSituation() {
        String message = game.guess(-5);
        assertEquals("You didn't get it - you're too low", message);
    }

    @Test
    public void testOneWrongPosGuessSituation() {
        int randomNumber = game.getRandomNumber();
        String message = game.guess(randomNumber + 1);
        assertEquals("You didn't get it - you're too high", message);
    }

    @Test
    public void testRandomNumberGeneration() {
        int[] numbersArray = new int[11];
        for (int i = 0; i < GAME_RANDOMNESS_RETRIES; i++) {
            GuessingGame localGame = new GuessingGame();
            int randomNumber = localGame.getRandomNumber();
            numbersArray[randomNumber] = 1;
        }

        int sum = 0;
        for (int i = 0; i < numbersArray.length; i++) {
            sum += numbersArray[i];
        }
        System.out.println(sum);
        assertEquals(10, sum);
    }

    @Test
    public void testFourWrongGuesses() {
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        String message = game.guess(-4);
        assertEquals("You didn't get it and you had 4 tries. Game over.", message);
    }

    @Test
    public void testThreeWrongGuessesAndOneCorrect() {
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You got it in 4 tries", message);
    }

    @Test
    public void testTwoWrongGuessesAndOneCorrect() {
        game.guess(-4);
        game.guess(-4);
        int correctAnswer = game.getRandomNumber();
        String message = game.guess(correctAnswer);
        assertEquals("You got it in 3 tries", message);
    }

    @Test
    public void testTenWrongGuesses() {
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        game.guess(-4);
        String message = game.guess(-4);
        assertEquals("Sorry, you are limited to only 4 tries. Your game is over.", message);
    }
}
