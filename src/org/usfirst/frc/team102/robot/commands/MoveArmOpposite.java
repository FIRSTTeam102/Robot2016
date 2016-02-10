package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArmOpposite extends Command {

	double speed;
	int armNumber;

	public MoveArmOpposite(double speed, int armNumber) {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.robotArm);
		this.speed = speed;
		this.armNumber = armNumber;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		try {
			Robot.robotArm.startArm(-speed, armNumber);
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}