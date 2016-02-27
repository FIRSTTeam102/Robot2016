package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.TimeUtil;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Scale extends Command {
	
	public Scale() {
		requires(Robot.scale);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		if(TimeUtil.isElapsed("Scale")) {
			Robot.scale.stopScaling();
			TimeUtil.remove("Scale");
		}
		
		Joystick driver = Robot.oi.getDriverXBox();
		Joystick operator = Robot.oi.getOperatorXBox();
		
		double driverLAxis = driver.getRawAxis(RobotMap.xBoxLeftTriggerAxis);
		double driverRAxis = driver.getRawAxis(RobotMap.xBoxRightTriggerAxis);
		double operatorLAxis = operator.getRawAxis(RobotMap.xBoxLeftTriggerAxis);
		double operatorRAxis = operator.getRawAxis(RobotMap.xBoxRightTriggerAxis);
		
		boolean timeExpired = (TimeUtil.has("Scale Timeout") ? TimeUtil.isElapsed("Scale Timeout") : true);
		
		if(driverLAxis == 1 && driverRAxis == 0 && operatorLAxis == 1 && operatorRAxis == 0 && timeExpired) {
			if(TimeUtil.has("Scale Timeout")) TimeUtil.remove("Scale Timeout");
			
			TimeUtil.setTimer("Scale", RobotMap.scaleTime);
			Robot.scale.startScaling(true);
			
			TimeUtil.setTimer("Scale Timeout", RobotMap.scaleTime + 1);
		}
		
		if(driverLAxis == 0 && driverRAxis == 1 && operatorLAxis == 0 && operatorRAxis == 1 && timeExpired) {
			if(TimeUtil.has("Scale Timeout")) TimeUtil.remove("Scale Timeout");
			
			TimeUtil.setTimer("Scale", RobotMap.scaleTime);
			Robot.scale.startScaling(false);
			
			TimeUtil.setTimer("Scale Timeout", RobotMap.scaleTime + 1);
		}
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.scale.stopScaling();
	}
	
	protected void interrupted() {
		end();
	}
}