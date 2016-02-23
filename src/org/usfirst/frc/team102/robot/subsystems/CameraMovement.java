package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.CameraWithXBox;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.IMAQdxCameraControlMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraMovement extends Subsystem {

	private Servo xAxis, yAxis;
	//private CameraServer cs;
	
	private double xPos = .5, yPos = 1;
	
	private Image img;
	private int currCam = -1, ssid = -1;
	
	public CameraMovement() {
		xAxis = new Servo(RobotMap.xAxisServo);
		yAxis = new Servo(RobotMap.yAxisServo);
		
		/*cs = CameraServer.getInstance();
		cs.setQuality(50);
		cs.startAutomaticCapture("cam" + RobotMap.frontCameraID);*/
		
		if(RobotMap.hasUSBCams) {
			img = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
			
			Thread camThread = new Thread(() -> {
				while(true) {
					if(currCam == -1 || ssid == -1 || !Robot.isRobotActive) {
						try {
							Thread.sleep(10);
						} catch (Exception e) {
							DriverStation.reportError("Error while preforming sleep: ", false);
							e.printStackTrace();
						}
					} else {
						NIVision.IMAQdxGrab(ssid, img, 1);
						CameraServer.getInstance().setImage(img);
					}
				}
			});
			
			camThread.setDaemon(true);
			camThread.setName("Camera Capturing Manager Daemon");
			camThread.start();
			
			setActiveCamera(1);
		}
	}
	
	public void setActiveCamera(int camNum) {
		if(!RobotMap.hasUSBCams) return;
		
		if(currCam != -1) stopCam(currCam);
		startCam(camNum);
		
		//cs.startAutomaticCapture("cam" + camNum);
	}
	
	private void stopCam(int num) {
		try {
			NIVision.IMAQdxStopAcquisition(ssid);
			NIVision.IMAQdxCloseCamera(ssid);
		} catch(Exception e) {
			System.err.println("Camera switch failed in quit: ");
			e.printStackTrace();
		}
	}
	
	private void startCam(int num) {
		try {
			ssid = NIVision.IMAQdxOpenCamera("cam" + num, IMAQdxCameraControlMode.CameraControlModeController);
			NIVision.IMAQdxConfigureGrab(ssid);
			NIVision.IMAQdxStartAcquisition(ssid);
			
			currCam = num;
		} catch(Exception e) {
			System.err.println("Camera switch failed in initializion: ");
			e.printStackTrace();
		}
	}
	
	public void moveCamWithXBox(Joystick js) {
		double x = js.getRawAxis(RobotMap.xBoxLeftXAxis);
		double y = js.getRawAxis(RobotMap.xBoxLeftYAxis);

		double delta = 0.02;

		if (x > .25 && xPos < .95)
			xPos += delta;
		if (x < -.25 && xPos > .05)
			xPos -= delta;
		if (y > .5 && yPos < .95)
			yPos += delta;
		if (y < -.5 && yPos > .05)
			yPos -= delta;

		xAxis.set(xPos);
		yAxis.set(yPos);
	}

	public void setServo(int id, double val) {
		if (id == 0)
			xAxis.set(val);
		if (id == 1)
			yAxis.set(val);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new CameraWithXBox());
	}
}