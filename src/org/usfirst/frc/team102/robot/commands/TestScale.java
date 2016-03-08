package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestScale extends Command {

	public double speed;
	
    public TestScale(double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.scale);
        this.setTimeout(3.0);
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.scale.startScaling(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.scale.stopScaling();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
