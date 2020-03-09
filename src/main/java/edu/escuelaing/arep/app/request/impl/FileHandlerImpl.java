package edu.escuelaing.arep.app.request.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import edu.escuelaing.arep.app.request.FileHandler;;

public class FileHandlerImpl implements FileHandler {
	
	
	@Override
	public void fileHandler(File file, String ext, Socket socket) throws IOException {
		System.out.println("here " + ext);
		if( ext.equals("png") ) {
			imageHandler(file, ext, socket);
		}else if( ext.equals("html")) {
			textHandler(file, ext, socket);
		}
		
	}
	
	public void textHandler(File file, String ext, Socket socket) throws IOException {
		String header = "HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: text/" + ext + "\r\n\r\n";
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		out.println(header);
		BufferedReader br = new BufferedReader(new FileReader(file));

		StringBuilder stringBuilder = new StringBuilder();
		String st;
		while ((st = br.readLine()) != null) {
			stringBuilder.append(st);
		}
		out.println(stringBuilder.toString());
		br.close();
	}
	
	public void imageHandler(File file, String ext, Socket socket) throws IOException {
		String header = "HTTP/1.1 200 \r\nAccess-Control-Allow-Origin: *\r\nContent-Type: image/" + ext
				+ "\r\nConnection: close\r\nContent-Length:" + file.length() + "\r\n\r\n";
		FileInputStream fileIn = new FileInputStream(file);
		OutputStream os = socket.getOutputStream();
		for (char c : header.toCharArray()) {
			os.write(c);
		}
		int a;
		while ((a = fileIn.read()) > -1) {
			os.write(a);
		}
		os.flush();
		fileIn.close();
		os.close();
	}

	
}
