package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.subsystems.BallHandler;

import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
	public ShootBall() { requires(Robot.robotRelay); setTimeout(.125); if(!BallHandler.direction) cancel(); }
	
	protected void initialize() { Robot.robotRelay.setRelay(true); BallHandler.direction = false; }
	protected void execute() {}
	protected boolean isFinished() { return isTimedOut(); }
	protected void end() { Robot.robotRelay.stop(); }
	protected void interrupted() { end(); }
}