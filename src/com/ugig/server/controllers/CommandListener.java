package com.ugig.server.controllers;

import java.util.Scanner;

/**
 * Listen and read the command from CLI
 */
public class CommandListener implements Runnable {

    @Override
    public void run() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            if (inputScanner.next().equals("exit")) {
                System.exit(0);
            } else {
                System.out.println("Invalid command");
            }
        }
    }
}
