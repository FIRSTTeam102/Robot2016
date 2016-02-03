package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;

public class GetInitialData extends Command {
	
	protected void initialize() {
		Autonomous.inputDataValue = 0;
		
		DigitalInput in = new DigitalInput(RobotMap.cfgSw0);
		if(in.get()) Autonomous.initialPosition++;
		
		in = new DigitalInput(RobotMap.cfgSw1);
		if(in.get()) Autonomous.initialPosition += 2;
		
		in = new DigitalInput(RobotMap.cfgSw2);
		if(in.get()) Autonomous.initialPosition += 4;
		
		in = new DigitalInput(RobotMap.cfgSw3);
		if(in.get()) Autonomous.initialPosition += 8;
		
		in = new DigitalInput(RobotMap.cfgSw4);
		if(in.get()) Autonomous.initialPosition += 16;
		
		in = new DigitalInput(RobotMap.cfgSw5);
		if(in.get()) Autonomous.initialPosition += 32;
		
		in = new DigitalInput(RobotMap.cfgSw6);
		if(in.get()) Autonomous.initialPosition += 64;
		
		in = new DigitalInput(RobotMap.cfgSw7);
		if(in.get()) Autonomous.initialPosition += 128;
		
		in = null;
		
		
	}
	
	protected void execute() {}
	protected boolean isFinished() { return true; }
	protected void end() {}
	protected void interrupted() {}
}