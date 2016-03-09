package org.usfirst.frc.team102.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team102.robot.commands.*;
import org.usfirst.frc.team102.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;

	public static DriveTrain robotDriveTrain;
	public static Arm robotArm;
	public static CameraMovement robotCam;
	public static BallHandler robotBallHandler;
	public static Scaling scale;

	public static Joystick driverJoystick;
	public static Joystick operatorJoystick;
	public static Command autonomousCommand = null;
	
	public static boolean isRobotActive = false;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		try {
			System.out.println("Enodia will block your path!");
			robotDriveTrain = new DriveTrain(.04, 0, 0);
			robotArm = new Arm();
			robotCam = new CameraMovement();
			robotBallHandler = new BallHandler();
			scale = new Scaling();
			oi = new OI();
			
			TimeUtil.init();
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		try {
			//if(autonomousCommand != null) {
				//autonomousCommand.cancel();
				//autonomousCommand = null;
			//}
			
			int i = 0;
			DigitalInput in = new DigitalInput(RobotMap.autoSwitch0);

			if (in.get())
				i++;

			in.free();
			in = new DigitalInput(RobotMap.autoSwitch1);

			if (in.get())
				i += 2;

			in.free();
			in = new DigitalInput(RobotMap.autoSwitch2);

			if (in.get())
				i += 4;

			in.free();
			in = new DigitalInput(RobotMap.autoSwitch3);

			if (in.get())
				i += 8;

			in.free();
			in = null;

			//System.out.println(i);

			if (i == 0)
				autonomousCommand = null;
			if (i == 1)
				autonomousCommand = new LowBar();
			if (i == 2)
				autonomousCommand = new DefensePos2('B');
			if (i == 3)
				autonomousCommand = new DefensePos2('D');
			if (i == 4)
				autonomousCommand = new DefensePos3('B');
			if (i == 5)
				autonomousCommand = new DefensePos3('D');
			if (i == 6)
				autonomousCommand = new DefensePos4('B');
			if (i == 7)
				autonomousCommand = new DefensePos4('D');
			if (i == 8)
				autonomousCommand = new DefensePos5('B');
			if (i == 9)
				autonomousCommand = new DefensePos5('D');
			if (i == 10)
				autonomousCommand = null;
			if (i == 11)
				autonomousCommand = null;
			if (i == 12)
				autonomousCommand = null;
			if (i == 13)
				autonomousCommand = null;
			if (i == 14)
				autonomousCommand = null;
			if (i == 15)
				autonomousCommand = null;

			if (autonomousCommand != null)
				autonomousCommand.start();
			
			System.out.println(i);
			isRobotActive = true;
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if(autonomousCommand != null && !autonomousCommand.isCanceled()) Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		try {
			updateMessages();
			if (autonomousCommand != null)
				autonomousCommand.cancel();
			
			isRobotActive = true;
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateMessages();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		isRobotActive = false;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void updateMessages(){
		robotBallHandler.updateDashboard();
		robotArm.updateDashboard();
		scale.updateDashboard();
		
	}
}