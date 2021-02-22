package com.testSuite;

import java.util.concurrent.*;

public class Main {

	static final int NUM_PROCS = Runtime.getRuntime().availableProcessors();

	public static void main(String[] args) throws InterruptedException {
	    int timesToLoop = 10;
	    int dSize = 1000;
	    int start = 10;
	    int end = 5000;

	    var threadPool = Executors.newFixedThreadPool(NUM_PROCS);
		TestRunner[] tasks = new TestRunner[(int) Math.ceil((end - start) / dSize)];

		for(int i = 0; i < tasks.length; ++i) {
			tasks[i] = new TestRunner("Thread" + i, (i * dSize) + start, timesToLoop);
			threadPool.execute(tasks[i]);
		}

		threadPool.shutdown();

		try {
			threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		}
		catch (InterruptedException e) {
			// shit
		}

		for(TestRunner test : tasks) {
			test.printTime();
		}
	}
}
