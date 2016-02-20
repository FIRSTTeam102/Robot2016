package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveHoop extends Command {

	private boolean direction;

	public MoveHoop(boolean direction) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.robotHoop);
		this.direction = direction;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		try {
			Robot.robotHoop.setRelay(direction);
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.robotHoop.checkLimitSwitches();
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
