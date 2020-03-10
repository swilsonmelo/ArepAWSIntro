package edu.escuelaing.arep.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Client extends Thread {

	private URL url;	
	
	private String requestContent;
	
	public Client(URL url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				sb.append(inputLine);
			}			
			requestContent = sb.toString();
			in.close();
			connection.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getrequestContent() {
		return requestContent;
	}

}
