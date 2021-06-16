package com.ugig.server;

import com.ugig.server.controllers.CommandListener;
import com.ugig.server.services.MainService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new MainService(3000);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server start error");
        }
        new Thread(new CommandListener()).start();
    }
}
