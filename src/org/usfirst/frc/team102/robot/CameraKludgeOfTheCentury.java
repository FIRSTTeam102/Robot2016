package org.usfirst.frc.team102.robot;

import java.lang.reflect.Field;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class CameraKludgeOfTheCentury {
	
	private static CameraServer camServ;
	private static Thread camServThread;
	
	public static void init(int camId) {
		try{
			camServ = CameraServer.getInstance();
			camServ.startAutomaticCapture("cam" + camId);
		
			camServThread = getThreadWithName("CameraServer Send Thread");
		}catch(Exception e){
			
		}
	}
	
	public static void setCamera(int id) { setCamera("cam" + id); }
	
	/**
	Before you comment on how kludge-y this is and how it relies on deprecated methods,
	consider the fact that it works and it wouldn't work any other way.
	*/
	@SuppressWarnings("deprecation")
	public static boolean setCamera(String name) {
		try {
			camServThread.suspend(); // Do this for "safety" while comodding their values
			
			Class<?> c = camServ.getClass();
			Field camField = c.getDeclaredField("m_camera"); // Camera field.
			camField.setAccessible(true);
			
			USBCamera currCam = (USBCamera)camField.get(camServ); // Get current
			currCam.stopCapture(); // Stop
			currCam.closeCamera(); // Quit
			
			USBCamera usbCam = new USBCamera(name); // Get new
			usbCam.openCamera(); // Open
			usbCam.startCapture(); // Start
			camField.set(camServ, usbCam); // Set new
			
			camServThread.resume(); // Allow it to continue
			
			return true;
		} catch(Throwable t) {
			System.err.println("Uh oh, the reflection did a bad thing! (Sorry..)");
			t.printStackTrace();
			
			return false;
		}
	}
	
	public static Thread getThreadWithName(String name) {
		ThreadGroup group = getRootThreadGroup();
		
		Thread[] tmp = new Thread[group.activeCount()];
		group.enumerate(tmp);
		for(Thread th : tmp) if(th.getName().equalsIgnoreCase(name)) return th;
		
		return null;
	}
	
	public static ThreadGroup getRootThreadGroup() {
		ThreadGroup group = Thread.currentThread().getThreadGroup();
		while(group.getParent() != null) group = group.getParent();
		
		return group;
	}
}