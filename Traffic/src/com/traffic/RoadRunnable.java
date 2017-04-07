package com.traffic;

/*
 * RoadRunnable Class implements runnable interface and extends TrafficLight.
 * where cars are coming from all four directions.
 * Author : Hitesh P Balkawade
 * version    : 1.0
 */
public class RoadRunnable extends TrafficLight implements Runnable {

private String roadNumber;
private TrafficLight light;

public RoadRunnable(String roadNum, TrafficLight aLight) {
	roadNumber = roadNum;
    light = aLight;
}

/*
 * (non-Javadoc)
 * @see java.lang.Runnable#run()
 */

public void run() {
        light.turnGreen(roadNumber);
        light.turnRed(roadNumber);
}

}

