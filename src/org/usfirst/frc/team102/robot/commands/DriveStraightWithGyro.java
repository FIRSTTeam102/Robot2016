package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightWithGyro extends Command {
	
	private int distance;
	private boolean done = false;
	
	public DriveStraightWithGyro(/*double timeout*/ int inches) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.robotDriveTrain);
		//setTimeout(timeout);
		distance = inches;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		try {
			//Robot.robotDriveTrain.startDriving(false);
			Robot.robotDriveTrain.resetEncoder();
			
			while(Robot.robotDriveTrain.getEncoderInches() > 0) {
				//System.out.println(Robot.robotDriveTrain.getEncoderInches());
				Thread.sleep(20);
			}
			
			Robot.robotDriveTrain.setDriveStraight();
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.robotDriveTrain.getEncoderInches() >= distance) {
			done = true;
			Robot.robotDriveTrain.resetEncoder();
		}
		
		//System.out.println(Robot.robotDriveTrain.getEncoderInches());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return isTimedOut();
		//return Robot.robotDriveTrain.getEncoderInches() >= distance;
		return done;
	}

	// Called once after isFinished returns true
	protected void end() {
		//Robot.robotDriveTrain.stop();
		Robot.robotDriveTrain.disable();
		Robot.robotDriveTrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}