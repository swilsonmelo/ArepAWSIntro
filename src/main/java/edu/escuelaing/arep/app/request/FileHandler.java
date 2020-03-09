package edu.escuelaing.arep.app.request;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public interface FileHandler {

    /**
     * Handles resources
     * @param resource A file path to the resource
     * @param ext The extension of the resource
     * @param socket The client socket
     * @throws IOException
     */
	void fileHandler(File file, String ext, Socket socket) throws IOException;
	    
}