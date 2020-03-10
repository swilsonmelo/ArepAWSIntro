package edu.escuelaing.arep.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class App {
	
	private static int numThreads;
	
	public static void main(String[] args) throws Exception {
		URL url = new URL(args[0]);
		numThreads = Integer.parseInt(args[1]);
		
		System.out.println(url + " " + numThreads);
		Client[] clients = new Client[numThreads];	
		
		for(int i = 0; i < numThreads; i++) {
			clients[i] = new Client(url);			
		}
		
		long tStart = System.currentTimeMillis();
		
		for(int i = 0; i < numThreads; i++) {
			clients[i].run();
		}
		
		for(int i = 0; i < numThreads; i++) {
			clients[i].join();
			/*
			System.out.println("Client " + i);			
			System.out.println(clients[i].getrequestContent());
			*/
		}
		
		long tFinished = System.currentTimeMillis();
		double totalTime = (double)((tFinished-tStart)/1000.0);
		System.out.println("Time(seconds): " + (tFinished - tStart) + " " + totalTime);		

	}

}
