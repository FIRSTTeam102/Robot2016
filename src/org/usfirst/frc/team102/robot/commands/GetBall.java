package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GetBall extends Command {
	
	public GetBall() { requires(Robot.robotRelay); setTimeout(.125); }
	
	protected void initialize() { Robot.robotRelay.setRelay(false); }
	protected void execute() {}
	protected boolean isFinished() { return isTimedOut(); }
	protected void end() { Robot.robotRelay.stop(); }
	protected void interrupted() {}
}