package com.ugig.server.services;

import com.ugig.server.controllers.AbstractHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class Receiver implements Runnable {
    private final Socket conn;
    private final HashMap<Integer, AbstractHandler> handlers;


    public Receiver(Socket conn, HashMap<Integer, AbstractHandler> handlers) {
        this.conn = conn;
        this.handlers = handlers;
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
        boolean argStart = false;
        int operator = 0;
        ArrayList<String> args = new ArrayList<>();//参数列表
        ArrayList<Byte> arg = null;
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
            // handle data
            for (int i = 0; i < buffer.length; i++) {
                // begin of data
                if (buffer[i] == 0x01) {
                    args.clear();
                    operator = buffer[++i];
                    // check if operator valid, invalid operator code range
                    if (operator >= 0x10 && handlers.containsKey(operator))
                        start = true;
                    continue;
                }

                if (start) {
                    // end of data
                    if (buffer[i] == 0x04) {
                        start = false;
                        argStart = false;
                        handlers.get(operator).exec(args);
                    } else if (buffer[i] == 0x02) {
                        argStart = true;
                        arg = new ArrayList<>();
                    } else if (argStart && buffer[i] == 0x03) {
                        argStart = false;
                        if (arg != null && !arg.isEmpty()) {
                            byte[] bytes = new byte[arg.size()];
                            for (int j = 0; j < bytes.length; j++) {
                                bytes[j] = arg.get(j);
                            }
                            args.add(new String(bytes, Charset.defaultCharset()));
                        }
                    } else if (argStart) {
                        arg.add(buffer[i]);
                    }

                }
            }

        }


    }
}
