package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.subsystems.BallHandler;

import edu.wpi.first.wpilibj.command.Command;

public class MoveBall extends Command {
	
	public MoveBall() { 
		requires(Robot.robotRelay);
		setTimeout(.125);
	}
	
	protected void initialize() { 
		Robot.robotRelay.setRelay(BallHandler.direction); 
		BallHandler.direction = !BallHandler.direction;
	}
	
	protected void execute() {}
	protected boolean isFinished() { return isTimedOut(); }
	protected void end() { Robot.robotRelay.stop(); }
	protected void interrupted() { end(); }
}