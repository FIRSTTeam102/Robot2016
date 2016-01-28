package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Rumbler;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CommandRumble extends Command {
	
	private final Rumbler theRumbler;
	private final Rumbles rType;
	
	public CommandRumble(Joystick js, Rumbles r) {
		theRumbler = new Rumbler(js);
		rType = r;
	}
	
	protected void execute() { 
		try{
		theRumbler.playRumbleMessage(rType); 
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
	}
	protected boolean isFinished() { 
		
		return true; 
		
	}
	protected void initialize() {
		
		
		
	}
	protected void end() {
		
		
		
	}
	protected void interrupted() {
		
		
		
	}
}