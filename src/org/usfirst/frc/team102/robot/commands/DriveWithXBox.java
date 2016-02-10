package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithXBox extends Command {

	public DriveWithXBox() {
		requires(Robot.robotDriveTrain);
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		try {
			if (Robot.robotDriveTrain == null) {
				System.out.println("Medusa is here");
			}

			if (Robot.oi == null) {
				System.out.println("Zeus threw a lightning bolt");
			}
			Robot.robotDriveTrain.driveWithXBox(Robot.oi.getDriverXBox());
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
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
