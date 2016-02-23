package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

public class CommandToggleReverse extends Command {

	public CommandToggleReverse() {
		requires(Robot.robotCam);
		requires(Robot.robotDriveTrain);
	}

	protected void initialize() {
		try {
			Robot.robotDriveTrain.isReverse = !Robot.robotDriveTrain.isReverse;
			Robot.oi.opRumble.playRumbleMessage(Robot.robotDriveTrain.isReverse ? Rumbles.reverse : Rumbles.forward);
			//Robot.robotCam.setServo(0, Robot.robotDriveTrain.isReverse ? .9 : .1);
			Robot.robotCam.setActiveCamera(Robot.robotDriveTrain.isReverse ? RobotMap.backCameraID : RobotMap.frontCameraID);
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