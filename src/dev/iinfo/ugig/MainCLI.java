package dev.iinfo.ugig;

import java.awt.*;
import javax.swing.*;

public class MainCLI {
    public static void main(String[] args) {
        GameCore game = new GameCore(30);
        Scanner sc = new Scanner(System.in);
        System.out.println("Game begin:");
        GameGuessResult result;
        while (true) {
            int guessNumber;
            try {
                String guess = sc.next();
                if (guess.equals("exit")) {
                    return;
                } else if (guess.equals("key")) {
                    System.out.println("Secret:" + game.getSecretNumber());
                    continue;
                }
                guessNumber = Integer.parseInt(guess);
                result = game.guess(guessNumber);
            } catch (Exception e) {
                System.out.println("Invalid input");
                continue;
            }
        }
    }
}
