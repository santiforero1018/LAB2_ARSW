package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		PrimeFinderThread pft1=new PrimeFinderThread("hilo 1",0, 9999999);
		PrimeFinderThread pft2=new PrimeFinderThread("hilo 2",10000000, 19999999);
		PrimeFinderThread pft3=new PrimeFinderThread("hilo 3",20000000, 30000000);

		pft1.start();
		pft2.start();
		pft3.start();

		
		System.out.println("Execution paused. Press ENTER to resume...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();

		pft1.running();
		pft2.running();
		pft3.running();
		

        

	}
	
}
