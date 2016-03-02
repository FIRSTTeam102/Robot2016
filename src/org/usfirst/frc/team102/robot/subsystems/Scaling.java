package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.Scale;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Scaling extends Subsystem {
	
	public Talon leftScaleTal, rightScaleTal;
	
	public Scaling() {
		leftScaleTal = new Talon(RobotMap.leftScaleTalon);
		rightScaleTal = new Talon(RobotMap.rightScaleTalon);
	}
	
	protected void initDefaultCommand() {
		setDefaultCommand(new Scale());
	}
	
	//scale speed is defined in the RobotMap
	public void startScaling(boolean direction) {
		if(direction){
			leftScaleTal.set(1);
		    rightScaleTal.set(1);
		} else {
		    leftScaleTal.set(-1);
			rightScaleTal.set(-1);    
		}
		
	}
	
	public void stopScaling(){
		leftScaleTal.set(0);
		rightScaleTal.set(0); 
	}
	
	public void updateDashbaord(){
		
		if(Scale.scale == Scale.scale.none)
			SmartDashboard.putString("DB/String 2", " ");
		else if(Scale.scale == Scale.ScaleState.tapeUp)
			SmartDashboard.putString("DB/String 2", "SCALE: TAPE UP");
		else if(Scale.scale == Scale.ScaleState.robotUp)
			SmartDashboard.putString("DB/String 2", "SCALE: ROBOT UP");
	}
}