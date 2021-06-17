package com.ugig.server;

import com.ugig.server.controllers.AbstractHandler;
import com.ugig.server.controllers.CommandListener;
import com.ugig.server.services.MainService;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, AbstractHandler> handlers = new HashMap<>();
        handlers.put(0x11, System.out::println);
        try {
            new MainService(3000, handlers);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server start error");
        }
        new Thread(new CommandListener()).start();
    }
}
