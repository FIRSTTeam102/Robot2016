package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CameraWithXBox extends Command {
	
	public CameraWithXBox() { requires(Robot.robotCam); }
	
	protected void initialize() {}
	protected void execute() { Robot.robotCam.moveCamWithXBox(Robot.oi.getOperatorXBox()); }
	protected boolean isFinished() { return false; }
	protected void end() {}
	protected void interrupted() {}
}