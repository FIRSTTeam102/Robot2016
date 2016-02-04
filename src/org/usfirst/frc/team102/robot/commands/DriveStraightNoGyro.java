package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */

public class DriveStraightNoGyro extends Command {
	
	double speed;
	
    public DriveStraightNoGyro(double speed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.robotDriveTrain);
    	this.setTimeout(timeout);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try{
    	Robot.robotDriveTrain.straightDrive(speed);
    }catch (Exception ex1){
    	ex1.printStackTrace();
		DriverStation.reportError(ex1.getMessage(), true);
    }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
