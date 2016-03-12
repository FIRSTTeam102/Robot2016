package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.OI;
import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.TimeUtil;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class Scale extends Command {
	
	public static ScaleState scale = ScaleState.none;
	public static boolean testScale = false;
	boolean running = false;
	
	public Scale() {
		requires(Robot.scale);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		if(TimeUtil.isElapsed("Scale")) {
			Robot.scale.stopScaling();
			scale = ScaleState.none;
			TimeUtil.remove("Scale");
			running = false;
		}	
		
		Joystick driver = Robot.oi.getDriverXBox();
		Joystick operator = Robot.oi.getOperatorXBox();
		Joystick tester = Robot.oi.getTesterXBox();

		
		double driverLAxis = driver.getRawAxis(RobotMap.xBoxLeftTriggerAxis);
		double driverRAxis = driver.getRawAxis(RobotMap.xBoxRightTriggerAxis);
		double operatorLAxis = operator.getRawAxis(RobotMap.xBoxLeftTriggerAxis);
		double operatorRAxis = operator.getRawAxis(RobotMap.xBoxRightTriggerAxis);	
	    double testLAxis = tester.getRawAxis(RobotMap.xBoxLeftTriggerAxis);
	    double testRAxis = tester.getRawAxis(RobotMap.xBoxRightTriggerAxis);
		
		//deadband
		//if(Math.abs(testLAxis) < 0.1)
		//	testLAxis = 0.0;
		//if(Math.abs(testRAxis) < 0.1)
			//testRAxis = 0.0;
		
		if(driverLAxis == 1 && driverRAxis == 0 && operatorLAxis == 1 && operatorRAxis == 0 && TimeUtil.isElapsed("Scale Timeout")) {
			TimeUtil.remove("Scale Timeout");

			TimeUtil.setTimer("Scale", RobotMap.scaleTime);
			Robot.scale.startScaling(1.0);
			TimeUtil.setTimer("Scale Timeout", RobotMap.scaleTime + 1);
			scale = ScaleState.tapeUp;
			running = true;
		}
		
		if(driverLAxis == 0 && driverRAxis == 1 && operatorLAxis == 0 && operatorRAxis == 1 && TimeUtil.isElapsed("Scale Timeout")) {
			TimeUtil.remove("Scale Timeout");

			TimeUtil.setTimer("Scale", RobotMap.scaleTime);
			Robot.scale.startScaling(-1.0);
			
			TimeUtil.setTimer("Scale Timeout", RobotMap.scaleTime + 1);
			scale = ScaleState.robotUp;
			running = true;
		}
		
		//scale manually in test-mode
		if(testRAxis * testLAxis == 0){
			
			if(testRAxis > 0 && driverLAxis == 0 && driverRAxis == 0 && operatorLAxis == 0 && operatorRAxis == 0){
				Robot.scale.startScaling(testRAxis);
			    testScale = true;
			}

			else if (testLAxis > 0 && driverLAxis == 0 && driverRAxis == 0 && operatorLAxis == 0 && operatorRAxis == 0){
				Robot.scale.startScaling(testLAxis);
			    testScale = true;
			}
		}
		
		//stop scale in test mode
		if(testRAxis == 0 && testLAxis == 0 && !running){
			testScale = false;
			Robot.scale.stopScaling();
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
	
	public enum ScaleState {
		
		none, robotUp, tapeUp;
	}
}