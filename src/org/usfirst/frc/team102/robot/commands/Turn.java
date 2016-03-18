package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
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
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.robotDriveTrain.resetEncoder();
		
		while(Robot.robotDriveTrain.getEncoderInches() > 0) {
			//System.out.println(Robot.robotDriveTrain.getEncoderInches());
			try {
				Thread.sleep(20);
			} catch(InterruptedException e) {
				DriverStation.reportError("Error while preforming sleep:", false);
				e.printStackTrace();
			}
		}
		
		Robot.robotDriveTrain.setTurnToAngle(degrees);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.robotDriveTrain.getInfo();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(Robot.autonomousCommand != null && Robot.autonomousCommand.isCanceled()) {
			Robot.robotDriveTrain.disable();
			Robot.robotDriveTrain.stop();
			return true;
		}
		
		//return isTimedOut();
		return Robot.robotDriveTrain.isDoneTurning;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.robotDriveTrain.disable();
		Robot.robotDriveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}