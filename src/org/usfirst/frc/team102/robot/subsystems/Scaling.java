package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.Scale;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Scaling extends Subsystem {
	
	public Talon leftScaleTal, rightScaleTal;
	
	public Scaling() {
		leftScaleTal = new Talon(RobotMap.leftScaleTalon);
		rightScaleTal = new Talon(RobotMap.rightScaleTalon);
	}
	
	protected void initDefaultCommand() {
	}
	
	public void startScaling(boolean dir) {
		
		//if(Scale.Dir.ArmsUp){
			leftScaleTal.set(1.0);
		    rightScaleTal.set(1.0);
		//}
		//if(Scale.Dir.RobotUp){
		    leftScaleTal.set(1.0);
			rightScaleTal.set(1.0);    
		//}
		
	}
	
	public void stopScaling(){
		
		leftScaleTal.set(1.0);
		rightScaleTal.set(1.0); 
		
	}
	
}