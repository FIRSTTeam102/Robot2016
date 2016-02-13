package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	CANTalon armMotor;
	private static DigitalInput limitSensorTop, limitSensorBottom;
	
	public Arm() {
		armMotor = new CANTalon(RobotMap.armMotor5);
		
		limitSensorTop = new DigitalInput(RobotMap.limitSensorTop);
		limitSensorBottom = new DigitalInput(RobotMap.limitSensorBottom);
	}

	public void initDefaultCommand() {
	}

	public void startArm(double speed) {
		//System.out.println(limitSensorTop.get() + " : " + limitSensorBottom.get());
		
		if(speed > 0 && !limitSensorTop.get()) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error); // "You can't do that!"
			return;
		}
		
		if(speed < 0 && !limitSensorBottom.get()) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error); // "You can't do that!"
			return;
		}
		
		armMotor.set(speed);
	}
	
	public void stopArm() {
		armMotor.set(0.0);
	}
}