package com.ugig.server.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Receiver implements Runnable {
    private final Socket conn;

    public Receiver(Socket conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        // 初始化输入输出流
        InputStream inputStream;
        OutputStream outputStream;
        try {
            inputStream = conn.getInputStream();
            outputStream = conn.getOutputStream();
        } catch (IOException e) {
            System.out.println("[INFO]Client disconnected:" + conn.getRemoteSocketAddress());
            return;
        }

        // 不断读取用户请求
        boolean start = false;
        int operator = 0;
        while (true) {
            byte[] buffer = new byte[32];
            // read and check disconnection
            try {
                if (-1 == inputStream.read(buffer)) {
                    System.out.println("[INFO]Client disconnected:" + conn.getRemoteSocketAddress());
                    break;
                }
            } catch (IOException e) {
                System.out.println("[INFO]Client disconnected:" + conn.getRemoteSocketAddress());
                break;
            }
            for (byte i: buffer) {

            }

        }


    }
}
