package com.testSuite;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import assign03.SimplePriorityQueue;

public class TestRunner implements Runnable {
    private final static Logger TEST_THREAD_LOG = Logger.getLogger((Logger.GLOBAL_LOGGER_NAME));

    private Thread mThread;
    private String mName;
    private int mNumLoop;
    private int mSize;
    private Random mRand;

    private double mAvgTime;

    public TestRunner(String name, int size, int numLoop) {
        mName = name;
        mSize = size;
        mNumLoop = numLoop;
        mRand = new Random(7); // NOTE: seeded
    }

    public void start() {
        if (mThread == null) {
            TEST_THREAD_LOG.log(Level.INFO, mName + " started ...");
            mThread = new Thread(this, mName);
            mThread.start();
        }
    }

    @Override
    public void run() {
        TEST_THREAD_LOG.log(Level.INFO, mName + " working...");

        SimplePriorityQueue<Integer> s =
                new SimplePriorityQueue<Integer>();

        for(int i = 0; i < mSize; i++)
            s.insert(mSize - i);    // best case of insert: logN + 1 -> O(logN)

        // First, spin computing stuff until one second has gone by.
        // This allows this thread to stabilize.

        long stopTime, midpointTime, startTime = System.nanoTime();

        while(System.nanoTime() - startTime < 1000000000) { // empty block
        }

        // Collect running times.
        startTime = System.nanoTime();
        for(int i = 0; i < mNumLoop; i++) {
            for(int j = 0; j < mSize; j++)
                s.insert(mSize - j);    // best case of insert: logN + 1 -> O(logN)

            //s.deleteMin();  // remove element from priority queue to keep size at N
        }

        midpointTime = System.nanoTime();

        // Capture the cost of running the loop and any other operations done
        // above that are not the essential method call being timed.
        for(int i = 0; i < mNumLoop; i++) {
            mRand.nextInt(mSize);
            s.deleteMin();
        }

        stopTime = System.nanoTime();

        // Compute the time, subtract the cost of running the loop
        // from the cost of running the loop and searching.
        // Average it over the number of runs.
        mAvgTime = ((midpointTime - startTime) -
                (stopTime - midpointTime)) / (double) mNumLoop;
        TEST_THREAD_LOG.log(Level.INFO, mSize + "\t" + mAvgTime);
    }

    public void printTime() {
        System.out.println(mSize + "\t" + mAvgTime);
    }
}


