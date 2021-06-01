package dev.iinfo.ugig.modles;

import java.util.ArrayList;
import java.util.Random;

public class GameCore {

    /**
     * const default max try times
     */
    private static final int DEFAULT_MAX_TIMES = 20;

    /**
     * State codes
     */
    public static final int OK = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int REPEAT = -2;

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
    private final int maxGuessTimes;

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
        secretNumber = new Random().nextInt(9000) + 1000;
    }


    /**
     * @param guessNumber the guess number
     * @param result      return the guess result record
     * @return 0: OK, one standard guess
     * 1: SUCCESS, guess totally right, game finished
     * -1: FAIL, Reached maximum guess times, game over
     * -2: REPEAT, number has been guessed
     * @throws IllegalArgumentException illegal guess number
     */
    public int guess(int guessNumber, GameRecord result) throws IllegalArgumentException {
        if (guessNumber > 9999 || guessNumber < 1000) {
            throw new IllegalArgumentException("illegal guess number");
        }

        // Guess totally right, game finished
        if (guessNumber == secretNumber) {
            result = new GameRecord(guessNumber, 4, 0);
            return SUCCESS;
        }

        // Check if has been guessed
        for (GameRecord history :
                histories) {
            if (history.number == guessNumber) {
                result = history;
                return REPEAT;
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

        // Build result
        result = new GameRecord(guessNumber, recordA, recordB);

        // Reached maximum guess times, game over
        if (++guessedTimes >= maxGuessTimes)
            return FAIL;

        // Standard guess situation
        return OK;
    }

    /**
     * restart the game
     */
    public void restart() {
        guessedTimes = 0;
        histories.clear();
        secretNumber = new Random().nextInt(9000) + 1000;
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
