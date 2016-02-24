package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class DriveStraightWithGyro extends Command {
	
	private int distance;
	@SuppressWarnings("unused")
	private int distanceAtLastFoot = -1, encoderAtLastFoot = -1;
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
		// "Polling" mode -- MUTUALLY EXCLUSIVE
		/*if(Robot.robotDriveTrain.getEncoderInches() + 12 > distance) {
			if(distanceAtLastFoot == -1) {
				distanceAtLastFoot = Robot.robotDriveTrain.getModulatedDistance();
				encoderAtLastFoot = Robot.robotDriveTrain.getEncoderInches();
			} else {
				int sensorDif = distanceAtLastFoot - Robot.robotDriveTrain.getModulatedDistance();
				int encoderDif = encoderAtLastFoot - Robot.robotDriveTrain.getEncoderInches();
				
				if(Math.abs(sensorDif - encoderDif) > 3) {
					DriverStation.reportError("Encoder and Distance Sensor do not agree with each other! Robot full-stopped.", false);
					Robot.robotDriveTrain.disable();
					Robot.robotDriveTrain.stop();
					
					if(Robot.autonomousCommand != null) Robot.autonomousCommand.cancel();
					
					done = true;
				}
			}
		}*/
		
		//System.out.println(Robot.robotDriveTrain.getModulatedDistance());
		
		// "Too close" mode -- MUTUALLY EXCLUSIVE
		if(Robot.robotDriveTrain.getModulatedDistance() != -1) {
			DriverStation.reportError("Distance sensor said that we are too close to the wall! Robot full-stopped.", false);
			Robot.robotDriveTrain.disable();
			Robot.robotDriveTrain.stop();
			
			if(Robot.autonomousCommand != null) Robot.autonomousCommand.cancel();
			
			done = true;
		}
		
		// Old version
		/*if(startDistance != -1 && Robot.robotDriveTrain.getModulatedDistance() != -1) {
			int distanceSensor = Robot.robotDriveTrain.getModulatedDistance() - startDistance;
			int encoder = Robot.robotDriveTrain.getEncoderInches();
			
			if(Math.abs(distanceSensor - encoder) > 24) {
				DriverStation.reportError("Encoder and Distance Sensor are wildly off! Robot full-stopped.", false);
				Robot.robotDriveTrain.disable();
				Robot.robotDriveTrain.stop();
				
				done = true;
			}
		}*/
		
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
		if(Robot.autonomousCommand != null && Robot.autonomousCommand.isCanceled()) {
			Robot.robotDriveTrain.disable();
			Robot.robotDriveTrain.stop();
			return true;
		}
		
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