package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Dancing extends Command {

	double speed;
	int timeout;
	
    public Dancing(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.robotArm);
    	requires(Robot.robotDriveTrain);
    	
    	this.setTimeout(5.0);
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {	
    	timeout++;
    	 
    	if(timeout < 25) {
    		Robot.robotDriveTrain.danceDriveRight(0);
    		Robot.robotArm.startArm(-speed, 1);	
    		Robot.robotDriveTrain.danceDriveLeft(-speed);
    	} else if(timeout < 45){
    		Robot.robotDriveTrain.danceDriveLeft(0);
    		Robot.robotArm.startArm(speed, 1);
    		Robot.robotDriveTrain.danceDriveRight(-speed);
    	} else timeout = 0;
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
    	return isTimedOut(); 
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.robotArm.stopArm();
		Robot.robotDriveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { 
    	end(); 
    }
}