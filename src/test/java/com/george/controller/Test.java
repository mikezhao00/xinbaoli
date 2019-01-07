package com.george.controller;

public class Test {

	ThreadLocal<Long> longLocal = new ThreadLocal<Long>();
	ThreadLocal<String> stringLocal = new ThreadLocal<>();
	
	public void set(long a1,String s2) {
		longLocal.set(a1);
		stringLocal.set(s2);
	}
	public long getLong() {
		return longLocal.get();
	}
	
	public String getString() {
		return stringLocal.get();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Test test = new Test();
		test.set(23l,"dsf");
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				test.set(33l,"fd");
				
				System.out.println(test.getLong());
				System.out.println(test.getString());
			}
		});
		
		thread1.start();
		thread1.join();
		
		System.out.println(test.getLong());
		System.out.println(test.getString());
		
	}
}
