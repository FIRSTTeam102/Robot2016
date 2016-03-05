package org.usfirst.frc.team102.robot;

import java.util.HashMap;

import edu.wpi.first.wpilibj.DriverStation;

public class TimeUtil {
	
	private static HashMap<String, Double> timers = new HashMap<String, Double>();
	
	public static void setTimer(String name, double seconds) {
		timers.put(name, seconds);
	}
	
	public static boolean isElapsed(String name) {
		if(!has(name)) return true;
		
		return get(name) == 0;
	}
	
	public static void remove(String name) {
		if(has(name)) timers.remove(name);
	}
	
	public static double get(String name) {
		if(!has(name)) return -1;
		
		return timers.get(name);
	}
	
	public static boolean has(String name) {
		return timers.containsKey(name);
	}
	
	public static void init() {
		Thread th = new Thread(() -> {
			while(true) {
				while(!Robot.isRobotActive) try {
					Thread.sleep(1000);
				} catch(Exception e) {
					DriverStation.reportError("Could not sleep: ", false);
					e.printStackTrace();
				}
				
				for(String str : timers.keySet()) {
					double time = timers.get(str);
					
					if(time > 0) timers.put(str, time - .1);
					else timers.put(str, 0d);
					System.out.println(str + ":" + time);
				}
				
				try {
					Thread.sleep(100);
				} catch(Exception e) {
					DriverStation.reportError("Error in timer: ", false);
					e.printStackTrace();
				}
			}
		});
		
		th.setDaemon(true);
		th.setName("Timer Handler");
		th.start();
	}
}