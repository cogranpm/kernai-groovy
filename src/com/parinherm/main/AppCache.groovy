package com.parinherm.main

@Singleton
class AppCache {
	private Random random = new Random()
	final static int UPPER_LIMIT_INT_RANDOM = 400 
	
	int getRandomInt() {
		random.nextInt(UPPER_LIMIT_INT_RANDOM)
	}
	
	int getRandomInt(int upper) {
		random.nextInt(upper)
	}
}
