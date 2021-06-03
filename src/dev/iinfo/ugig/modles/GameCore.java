package dev.iinfo.ugig.modles;

import java.util.ArrayList;
import java.util.Random;

/**
 * the core of the guess number game
 */
public class GameCore {

    /**
     * const default max try times
     */
    private static final int DEFAULT_MAX_TIMES = 20;


    /**
     * the secret number
     */
    private int secretNumber;

    /**
     * record the number of guess
     */
    private int guessedTimes;

    /**
     * the maximum number of guess
     */
    private int maxGuessTimes;

    /**
     * store GameRecord history
     */
    private final ArrayList<GameRecord> histories;

    /**
     * Default constructor
     */
    public GameCore() {
        this(DEFAULT_MAX_TIMES);
    }

    /**
     * initial the game
     *
     * @param maxGuessTimes the maximum number of guess
     * @throws IllegalArgumentException the maximum number cannot less than 1
     */
    public GameCore(int maxGuessTimes) throws IllegalArgumentException {
        if (maxGuessTimes < 1)
            throw new IllegalArgumentException("the maximum number cannot less than 1");
        this.maxGuessTimes = maxGuessTimes;
        guessedTimes = 0;
        histories = new ArrayList<>();
        Random random = new Random();
        secretNumber = 0;
        for (int i = 0; i < 4; i++) {
            secretNumber = secretNumber * 10 + random.nextInt(9) + 1;
        }
    }


    /**
     * @param guessNumber the guess number
     * @return guess result
     * @throws IllegalArgumentException illegal guess number
     */
    public GameGuessResult guess(int guessNumber) throws IllegalArgumentException {
        if (guessNumber > 9999 || guessNumber < 1111) {
            throw new IllegalArgumentException("Illegal guess number");
        }

        // Guess totally right, game finished
        if (guessNumber == secretNumber) {
            return new GameGuessResult(GameGuessResult.SUCCESS, guessNumber, 4, 0);
        }

        // Check if has been guessed
        for (GameRecord history : histories) {
            if (history.number == guessNumber) {
                return new GameGuessResult(GameGuessResult.DUPLICATE, history);
            }
        }

        // Standard check
        boolean[] status = new boolean[4];
        int recordA = 0;
        int recordB = 0;
        int[] secret = new int[4];
        int[] guess = new int[4];
        // Restore int to int[] and check A
        for (int i = 0; i < 4; i++) {
            secret[i] = (int) (secretNumber / Math.pow(10, 3 - i)) % 10;
            guess[i] = (int) (guessNumber / Math.pow(10, 3 - i)) % 10;
            if (guess[i] == 0) {
                throw new IllegalArgumentException("Illegal guess number");
            }
            if (secret[i] == guess[i]) {
                status[i] = true;
                recordA++;
            }
        }
        // Check B
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!status[j] && guess[i] == secret[j]) {
                    status[j] = true;
                    recordB++;
                }
            }
        }

        GameRecord gameRecord = new GameRecord(guessNumber, recordA, recordB);
        // Reached maximum guess times, game over
        if (++guessedTimes >= maxGuessTimes)
            return new GameGuessResult(GameGuessResult.FAIL, gameRecord);

        // Standard guess situation
        histories.add(gameRecord);
        return new GameGuessResult(GameGuessResult.OK, gameRecord);
    }


    /**
     * Default restart method
     */
    public void restart() {
        restart(maxGuessTimes);
    }

    /**
     * restart the game
     */
    public void restart(int maxGuessTimes) throws IllegalArgumentException {
        if (maxGuessTimes < 1)
            throw new IllegalArgumentException("the maximum number cannot less than 1");
        this.maxGuessTimes = maxGuessTimes;
        guessedTimes = 0;
        histories.clear();
        Random random = new Random();
        secretNumber = 0;
        for (int i = 0; i < 4; i++) {
            secretNumber = secretNumber * 10 + random.nextInt(9) + 1;
        }
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public int getGuessedTimes() {
        return guessedTimes;
    }

    /**
     * @param index the index of history
     * @return history record
     */
    public GameRecord getHistory(int index) {
        return histories.get(index);
    }
}
