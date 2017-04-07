package com.trafficbothside;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * TrafficLight Class resembles a four point intersection.
 * where cars are coming from all four directions.
 * Author : Hitesh P Balkawade
 * version    : 2.0
 */
public class TrafficLight {

	private String rNumber;
	// Below four queue are for holding cars coming on each direction of road

	private BlockingQueue<String> northQueue = new ArrayBlockingQueue<String>(20);
	private BlockingQueue<String> southQueue = new ArrayBlockingQueue<String>(20);
	private BlockingQueue<String> westQueue = new ArrayBlockingQueue<String>(20);
	private BlockingQueue<String> eastQueue = new ArrayBlockingQueue<String>(20);

	private boolean signalStateChange = false;

	Object obj = new Object();

	public TrafficLight() {
	}

	/*
	 * trunGreen method will be called by subsequent road signal to make it
	 * green so car from that road side can travel and cars from other road side
	 * can halt. This Signal will be turn on for 3 seconds
	 * 
	 * @roadNumber : This take road number as input which is calling this signal
	 * to make it green.
	 */
	public void turnGreen(String roadNumber) {
		rNumber = roadNumber;
		synchronized (obj) {
			System.out.print("Light turned GREEN by road -->" + rNumber + "\n");
			try {
				long startTime = System.currentTimeMillis(); // fetch starting
																// time
				while ((System.currentTimeMillis() - startTime) < 3000) {

					if (roadNumber.equals("NS")) {
						if (northQueue.contains("car")) {
							northQueue.remove("car");
							southQueue.remove("car");
						}
						westQueue.put("car");
						eastQueue.put("car");
						Thread.sleep(1000);
						System.out.print("N = " + northQueue.size() + ";" + " S = " + southQueue.size() + ";" + " W = "
								+ westQueue.size() + ";" + " E = " + eastQueue.size() + ";" + "\n");
					}

					if (roadNumber.equals("WE")) {
						if (westQueue.contains("car")) {
							if (signalStateChange) {
								westQueue.put("car");
								eastQueue.put("car");
								signalStateChange =false;
							} else {
								westQueue.put("car");
								eastQueue.put("car");
								if (westQueue.size() > 1) {
									westQueue.remove("car");
									eastQueue.remove("car");

								}
							}
						}
						northQueue.put("car");
						southQueue.put("car");

						Thread.sleep(1000);
						System.out.print("N = " + northQueue.size() + ";" + " S = " + southQueue.size() + ";" + " W = "
								+ westQueue.size() + ";" + " E = " + eastQueue.size() + ";" + "\n");
					}
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/*
	 * turnRed method will be called by road whose previous signal was Green. so
	 * the running traffic can stop and after one second other road will call
	 * green signal. This signal will be turned on for 1 second
	 * 
	 * @roadNumber : This take road number as input which is calling this signal
	 * to make it red.
	 */
	public void turnRed(String roadNumber) {
		rNumber = roadNumber;
		synchronized (obj) {

			System.out.print("Light turned RED by road   -->" + rNumber + "\n");

			try {
				long startTime = System.currentTimeMillis(); // fetch starting
																// time
				while ((System.currentTimeMillis() - startTime) < 1000) {
					if (roadNumber.equals("NS")) {
						northQueue.put("car");
						southQueue.put("car");
						westQueue.put("car");
						eastQueue.put("car");
						System.out.print("N = " + northQueue.size() + ";" + " S = " + southQueue.size() + ";" + " W = "
								+ westQueue.size() + ";" + " E = " + eastQueue.size() + ";" + "\n");
						Thread.sleep(1000);
					}

					if (roadNumber.equals("WE")) {
						northQueue.put("car");
						southQueue.put("car");
						westQueue.put("car");
						eastQueue.put("car");

						System.out.print("N = " + northQueue.size() + ";" + " S = " + southQueue.size() + ";" + " W = "
								+ westQueue.size() + ";" + " E = " + eastQueue.size() + ";" + "\n");
						Thread.sleep(1000);
					}
					signalStateChange =true;
				}
			} catch (InterruptedException ex) {
			}
		}
	}

}
