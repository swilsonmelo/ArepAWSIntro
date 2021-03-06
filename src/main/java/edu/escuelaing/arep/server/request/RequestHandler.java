package edu.escuelaing.arep.server.request;

import org.apache.commons.io.FilenameUtils;

import edu.escuelaing.arep.server.request.impl.FileHandlerImpl;

import java.io.*;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestHandler {

    private Socket clientSocket;

    private BufferedReader in;

    private PrintWriter out;

    private FileHandler fileHandler;


    public RequestHandler(Socket socket) throws IOException {
        clientSocket = socket;        
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        fileHandler = new FileHandlerImpl();
    }

    public void readClientRequest() throws IOException{
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        Pattern pattern = Pattern.compile("GET (/[^\\s]*)");
        Matcher matcher = null;
        while ((inputLine = in.readLine()) != null) {
        	System.out.println("I received: " + inputLine);
            stringBuilder.append(inputLine);
            if (!in.ready()) {
                matcher = pattern.matcher(stringBuilder.toString());
                if (matcher.find()) {
                	String res = matcher.group();
                    System.out.println(res);
                    String fileRequested = matcher.group().substring(5);
                    System.out.println(fileRequested);
                    System.out.println("VALUE: " + fileRequested);
                    handleRequest(fileRequested);
                }
                break;
            }
        }        
        in.close();
        clientSocket.close();
    }
    
    private void handleRequest(String fileRequested) throws IOException {
        String filePath = "src/main/resources/";
        String ext = FilenameUtils.getExtension(fileRequested);        
        if (ext.equals("html")) {
            filePath += "web-pages/";
        } else if (ext.equals("png")) {
            filePath += "images/";            
        }
        filePath += fileRequested;
        File file = new File(filePath);    
    
        if (file.exists() && !file.isDirectory()) {
            fileHandler.fileHandler(file, ext, clientSocket);
        } else {
            returnFileNotFound(fileRequested);
        }
    }
    
    public void returnFileNotFound(String fileRequested){
        out.println("HTTP/1.1 404\r\nAccess-Control-Allow-Origin: *\r\n\r\n" 
                    + "<html>" 
                    + "<body>"
                        + "<h1>404 NOT FOUND ( " + fileRequested + " )</h1>" 
                    + "</body>" 
                    + "</html>");
    }

}