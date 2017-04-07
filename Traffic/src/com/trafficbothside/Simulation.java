package com.trafficbothside;

/*
 * Simulation Class is the starting point of application where two threads resembles Snell Rd and Weaver Rd.
 * where cars are coming from all four directions. And both side (N & S) or (E & W) traffic are moving at any given time
 * Author : Hitesh P Balkawade
 * version    : 2.0
 */
public class Simulation {

	private Thread thread1, thread2;

	public static void main(String[] args) {
		// At Starting of Signal zero time
		System.out.print("N = " + 0 + ";" + " S = " + 0 + ";" + " W = " + 0 + ";" + " E = " + 0 + ";" + "\n");
	//	while (true) {
			Simulation s = new Simulation();
			s.go();
	//	}

	}

	public Simulation() {
		TrafficLight light = new TrafficLight();
		RoadRunnable NS = new RoadRunnable("NS", light);
		RoadRunnable WE = new RoadRunnable("WE", light);
		thread1 = new Thread((Runnable) NS);
		thread2 = new Thread((Runnable) WE);

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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
