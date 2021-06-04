package dev.iinfo.ugig;

import dev.iinfo.ugig.modles.GameCore;
import dev.iinfo.ugig.modles.GameGuessResult;

import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        GameCore game = new GameCore(30);
        Scanner sc = new Scanner(System.in);
        System.out.println("Game begin:");
        GameGuessResult result;
        while (true) {
            int guessNumber;
            try {
                String guess = sc.next();
                if (guess.equals("exit"))
                    return;
                else if (guess.equals("key")) {
                    System.out.println("Secret:" + game.getSecretNumber());
                    continue;
                }
                guessNumber = Integer.parseInt(guess);
                result = game.guess(guessNumber);
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
            }

            if (result.getCode() == GameGuessResult.OK) {
                System.out.println("Guess time:" + game.getGuessedTimes());
                System.out.println(result.toString());
            } else if (result.getCode() == GameGuessResult.DUPLICATE) {
                System.out.println("Duplicate");
                System.out.println(result.toString());
            } else if (result.getCode() == GameGuessResult.SUCCESS) {
                System.out.println("You win!");
                System.out.println(result.toString());
                return;
            } else if (result.getCode() == GameGuessResult.FAIL) {
                System.out.println("Game over");
                System.out.println("Secret number: " + game.getSecretNumber());
                return;
            }

        }

    }
}
