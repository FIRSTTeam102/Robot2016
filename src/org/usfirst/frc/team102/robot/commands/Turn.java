package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	
	private double degrees;
	
	public Turn(double degrees) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		requires(Robot.robotDriveTrain);
		this.degrees = degrees;
		//setTimeout(.3);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.robotDriveTrain.setTurnToAngle(degrees);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return isTimedOut();
		return Robot.robotDriveTrain.isDoneTurning;
	}

	// Called once after isFinished returns true
	protected void end() {
//		if (!GetInitialData.noAuto)
//			Robot.robotDriveTrain.stop();
		Robot.robotDriveTrain.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}