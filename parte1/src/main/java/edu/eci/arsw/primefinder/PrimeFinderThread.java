package edu.eci.arsw.primefinder;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{

	
	int a,b;
	
	private List<Integer> primes=new LinkedList<Integer>();
	
	private Long startTime, endTime;

	public PrimeFinderThread(String name, int a, int b) {
		super(name);
		this.a = a;
		this.b = b;
	}

	@Override
	public void run(){
		this.startTime = System.currentTimeMillis();
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primes.add(i);
				System.out.println(this.getName() + ": " + i);
			}
		}
		
		
	}
	
	private synchronized boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
			
	    }
		this.endTime = System.currentTimeMillis();
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	private void pause() throws InterruptedException{
		Long start = System.currentTimeMillis();


	}
	
	private void running(){

	}
	
}
