package com.ugig.server.controllers;

import java.util.ArrayList;

public interface AbstractHandler {
    void exec(ArrayList<String> args) throws NullPointerException;
}
