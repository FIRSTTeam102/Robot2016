package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShootBall extends Command {
	
	public ShootBall() { requires(Robot.robotRelay); setTimeout(.125); }
	
	protected void initialize() { Robot.robotRelay.setRelay(true); }
	protected void execute() {}
	protected boolean isFinished() { return isTimedOut(); }
	protected void end() { Robot.robotRelay.stop(); }
	protected void interrupted() {}
}