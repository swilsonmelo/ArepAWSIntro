package edu.escuelaing.arep.app;

import edu.escuelaing.arep.app.request.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

    private static final int MAX_THREADS = 20;

    private ServerSocket serverSocket;

    private Socket clientSocket;

    private boolean running;
    
    private ExecutorService threadPool;

    
    public HttpServer() {
        int port = getPort();
        running = true;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
        threadPool = Executors.newFixedThreadPool(MAX_THREADS);
        clientSocket = null;
    }
    
    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port
    }

    public void run() throws IOException {
        while (running) {
            try {
                System.out.println("Ready to receive");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            RequestHandler requestHandler = new RequestHandler(clientSocket);
            Runnable runnable = () -> {
                try {
                    requestHandler.readClientRequest();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };

            threadPool.execute(runnable);

        }
        threadPool.shutdown();
        serverSocket.close();
    }
  

}