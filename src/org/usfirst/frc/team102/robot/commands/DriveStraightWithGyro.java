package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightWithGyro extends Command {
	
	double speed;
	
    public DriveStraightWithGyro(double speed, double timeout) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires (Robot.robotDriveTrain);
    	
    	if(GetInitialData.startingPos == 1)
    		timeout = 1; //dummy values for now
    	else if(GetInitialData.startingPos == 2)
    		timeout = 2;
    	else if(GetInitialData.startingPos == 3)
    		timeout = 3;
    	else if(GetInitialData.startingPos == 4)
    		timeout = 4;
    	
    	this.setTimeout(timeout);
    	this.speed = speed;
    	
    	if(GetInitialData.noAuto)
    		this.cancel();
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
