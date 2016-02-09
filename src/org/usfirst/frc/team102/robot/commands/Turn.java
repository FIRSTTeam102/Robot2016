package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Turn extends Command {
	
    public Turn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.robotDriveTrain);
    	setTimeout(.3);
    	if(GetInitialData.noAuto) cancel();
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if(GetInitialData.noAuto) return;
    	
    	if(GetInitialData.turn1) Robot.robotDriveTrain.startTurning(true);
    	if(GetInitialData.turn2) Robot.robotDriveTrain.startTurning(false);
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
    	if(!GetInitialData.noAuto) Robot.robotDriveTrain.stop(); 
    	}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { 
    	end(); 
    	}
}