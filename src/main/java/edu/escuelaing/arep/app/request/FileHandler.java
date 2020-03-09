package edu.escuelaing.arep.app.request;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

public interface FileHandler {

	void fileHandler(File file, String ext, Socket socket) throws IOException;
	    
}