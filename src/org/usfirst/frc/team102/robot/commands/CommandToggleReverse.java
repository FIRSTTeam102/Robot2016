package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.OI;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class CommandToggleReverse extends Command {
	
	public static boolean isReverse = false;
	
	protected void initialize() {
		try{
		isReverse = !isReverse;
		OI.opRumble.playRumbleMessage(isReverse ? Rumbles.reverse : Rumbles.forward);
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);

		}
		}
	
	protected void execute() {
		
		
	}
	
	protected boolean isFinished() { 
		
		return true; 
		
	}
	
	protected void end() {
		
		
		
	}
	protected void interrupted() {
		
		
		
	}
}