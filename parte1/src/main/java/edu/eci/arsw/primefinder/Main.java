package edu.eci.arsw.primefinder;



public class Main {

	public static void main(String[] args) {
		PrimeFinderThread pft1=new PrimeFinderThread("hilo 1",0, 9999999);
		PrimeFinderThread pft2=new PrimeFinderThread("hilo 2",10000000, 19999999);
		PrimeFinderThread pft3=new PrimeFinderThread("hilo 3",20000000, 30000000);
		
		pft1.start();
		pft2.start();
		pft3.start();
		
		
	}
	
}
