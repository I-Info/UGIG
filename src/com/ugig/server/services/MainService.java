package com.ugig.server.services;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainService {
    private final ServerSocket server;
    private final ExecutorService executors;


    public MainService(int port) throws IOException {
        executors = Executors.newCachedThreadPool(); // 初始化线程池

        //启动服务器，开始监听
        server = new ServerSocket(port);//异常由外部处理

        System.out.println("Server started at: ");
        System.out.println(server.getLocalSocketAddress());

        //接收客户端连接
        executors.execute(() -> {
            while (true) {
                try {
                    Socket conn = server.accept();
                    System.out.println("[INFO]Client connected: " + conn.getRemoteSocketAddress());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        // 添加关闭服务器时执行的钩子函数
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            executors.shutdown();
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Server closed");
        }));
    }

}
