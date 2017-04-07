package com.traffic;

/*
 * Simulation Class is the starting point of application where two threads resembles Snell Rd and Weaver Rd.
 * where cars are coming from all four directions. And only single side N, S, E or W traffic is moving at any given time
 * Author : Hitesh P Balkawade
 * version    : 1.0
 */
public class Simulation {

	private Thread thread1, thread2, thread3, thread4;

	public static void main(String[] args) {
		Simulation s = new Simulation();
		s.go();

	}

	public Simulation() {

		TrafficLight light = new TrafficLight();

		RoadRunnable N = new RoadRunnable("N", light);
		RoadRunnable S = new RoadRunnable("S", light);
		RoadRunnable W = new RoadRunnable("W", light);
		RoadRunnable E = new RoadRunnable("E", light);
		thread1 = new Thread((Runnable) N);
		thread2 = new Thread((Runnable) S);
		thread3 = new Thread((Runnable) W);
		thread4 = new Thread((Runnable) E);

	}

	/*
	 * go method is called inside main to start two threads
	 */
	public void go() {
		try {
			thread1.start();
			thread1.join();
			thread2.start();
			thread2.join();
			thread3.start();
			thread3.join();
			thread4.start();
			thread4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
