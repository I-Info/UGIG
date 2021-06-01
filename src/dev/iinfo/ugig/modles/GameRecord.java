package dev.iinfo.ugig.modles;

public class GameRecord {
    final int number;
    final int recordA;
    final int recordB;

    /**
     * @param number  guessed number
     * @param recordA "A" numbers
     * @param recordB "B" numbers
     */
    public GameRecord(int number, int recordA, int recordB) {
        this.number = number;
        this.recordA = recordA;
        this.recordB = recordB;
    }

    /**
     * @return guessed number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return number of "A"
     */
    public int getRecordA() {
        return recordA;
    }

    /**
     * @return number of "B"
     */
    public int getRecordB() {
        return recordB;
    }

    @Override
    public String toString() {
        return "GameRecord{" +
                "number=" + number +
                ", recordA=" + recordA +
                ", recordB=" + recordB +
                '}';
    }
}
