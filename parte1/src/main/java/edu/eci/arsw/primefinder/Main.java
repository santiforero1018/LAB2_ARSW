package edu.eci.arsw.primefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		PrimeFinderThread pft1 = new PrimeFinderThread("hilo 1", 0, 9999999);
		PrimeFinderThread pft2 = new PrimeFinderThread("hilo 2", 10000000, 19999999);
		PrimeFinderThread pft3 = new PrimeFinderThread("hilo 3", 20000000, 30000000);

		pft1.start();
		pft2.start();
		pft3.start();

		while (true) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			br.readLine();

			pft1.running();
			pft2.running();
			pft3.running();

			if (pft1.verify() && pft2.verify() && pft3.verify()) {
				System.out.println("Total primes of " + pft1.getName() + ": " + pft1.getPrimes().size());
				System.out.println("Total primes of " + pft2.getName() + ": " + pft2.getPrimes().size());
				System.out.println("Total primes of " + pft3.getName() + ": " + pft3.getPrimes().size());
				System.exit(0);
			}
		}

	}

}
