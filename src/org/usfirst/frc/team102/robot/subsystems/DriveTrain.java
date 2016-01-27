package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.CommandToggleReverse;
import org.usfirst.frc.team102.robot.commands.DriveWithXBox;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

import static org.usfirst.frc.team102.robot.commands.CommandToggleReverse.isReverse;

/**
 *
 */
public class DriveTrain extends Subsystem {

	CANTalon m1;
	CANTalon m2;
	private DigitalOutput isGoingForward, isGoingBackward;
	private double leftJoyX;
	private double leftJoyY;
	private double rightJoyX;
	private double rightJoyY;
	

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithXBox());
	}

	public DriveTrain() {
		m1 = new CANTalon(RobotMap.m1);
		m2 = new CANTalon(RobotMap.m2);
		
		isGoingForward = new DigitalOutput(RobotMap.forwardIndicator);
		isGoingBackward = new DigitalOutput(RobotMap.backwardIndicator);
	}
	
	public void driveWithXBox(Joystick xBox) {
		isGoingForward.set(!isReverse);
		isGoingBackward.set(isReverse);
		
		try {

			leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
			leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
			rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
			rightJoyY = xBox.getRawAxis(RobotMap.xBoxRightYAxis);

			leftJoyX *= 1.0;
			leftJoyY *= 1.0;
			rightJoyX *= 1.0;
			rightJoyY *= 1.0;

			if (Math.abs(leftJoyX) < 0.1) {
				leftJoyX = 0.0;
			}
			if (Math.abs(leftJoyY) < 0.1) {
				leftJoyY = 0.0;
			}
			if (Math.abs(rightJoyX) < 0.1) {
				rightJoyX = 0.0;
			}
			if (Math.abs(rightJoyY) < 0.1) {
				rightJoyY = 0.0;
			}

			m1.set(isReverse ? -leftJoyY : leftJoyY);
			m2.set(isReverse ? -rightJoyY : rightJoyY);
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
	}
}
