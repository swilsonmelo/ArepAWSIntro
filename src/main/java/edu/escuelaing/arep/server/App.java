package edu.escuelaing.arep.server;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException{
        HttpServer server = new HttpServer();
        server.run();
    }
}
