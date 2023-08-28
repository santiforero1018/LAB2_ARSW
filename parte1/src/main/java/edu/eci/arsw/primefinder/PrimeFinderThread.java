package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread {

	int a, b;

	private List<Integer> primes = new LinkedList<Integer>();
	private Object lock = new Object();
	private Long startTime;

	public PrimeFinderThread(String name, int a, int b) {
		super(name);
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		while (true) {
			this.startTime = System.currentTimeMillis();
			long targetPauseTime = startTime + 5000;
			synchronized (lock) {
				for (int i = this.a; i <= this.b && (System.currentTimeMillis() < targetPauseTime); i++) {
					if (isPrime(i)) {
						primes.add(i);
						// System.out.println(this.getName()+":"+i);
					}

					this.a = i;
				}

				if (System.currentTimeMillis() >= targetPauseTime) {
					try {
						System.out.println(this.getName() + ": " + this.getPrimes().size() + " current a: " +a);
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	private boolean isPrime(int n) {
		if (n % 2 == 0)
			return false;
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0)
				return false;

		}
		return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

	public boolean verify(){
		return this.a >= this.b;
	}

	public void running() {
		synchronized (lock) {
			lock.notify();
		}
	}

}
