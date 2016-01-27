package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.OI;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.command.Command;

public class CommandToggleReverse extends Command {
	
	public static boolean isReverse = false;
	
	protected void initialize() {
		isReverse = !isReverse;
		
		OI.opRumble.playRumbleMessage(isReverse ? Rumbles.reverse : Rumbles.forward);
	}
	
	protected void execute() {}
	protected boolean isFinished() { return true; }
	protected void end() {}
	protected void interrupted() {}
}