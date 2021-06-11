package com.ugig;

import com.ugig.modles.GameCore;
import com.ugig.modles.GameGuessResult;
import com.ugig.tests.TestView;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-CLI")) {
            mainCLI();
            return;
        }
//        new GameView();
        TestView testView = new TestView("main");
        testView.setVisible(true);
    }

    public static void mainCLI() {
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
                System.out.println(result);
            } else if (result.getCode() == GameGuessResult.DUPLICATE) {
                System.out.println("Duplicate");
                System.out.println(result);
            } else if (result.getCode() == GameGuessResult.SUCCESS) {
                System.out.println("You win!");
                System.out.println(result);
                return;
            } else if (result.getCode() == GameGuessResult.FAIL) {
                System.out.println("Game over");
                System.out.println("Secret number: " + game.getSecretNumber());
                return;
            }

        }
    }
}
