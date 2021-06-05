package dev.iinfo.ugig.modles;

/**
 * class for store guess result
 */
public class GameGuessResult {

    /**
     * State codes
     */
    public static final int OK = 0;
    public static final int SUCCESS = 1;
    public static final int FAIL = -1;
    public static final int DUPLICATE = -2;

    final int code;
    final int number;
    final int recordA;
    final int recordB;

    public GameGuessResult(int code, int number, int recordA, int recordB) {
        this.code = code;
        this.number = number;
        this.recordA = recordA;
        this.recordB = recordB;
    }

    public GameGuessResult(int code, GameRecord record) {
        this.code = code;
        this.number = record.number;
        this.recordA = record.recordA;
        this.recordB = record.recordB;
    }

    public int getCode() {
        return code;
    }

    public int getNumber() {
        return number;
    }

    public int getRecordA() {
        return recordA;
    }

    public int getRecordB() {
        return recordB;
    }

    @Override
    public String toString() {
        return "n=" + number +
                ", A=" + recordA +
                ", B=" + recordB;
    }
}
