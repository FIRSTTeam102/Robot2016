package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleHoop extends Command {

	private final boolean dir;
	private boolean done;
	
	public ToggleHoop(boolean direction) {
		requires(Robot.robotBallHandler);
		setTimeout(.25);

		dir = direction;
	}

	protected void initialize() {
		Robot.robotBallHandler.setRelay(dir);
	}

	protected void execute() {
		if(!RobotMap.isTestBed) {
			Robot.robotBallHandler.checkLimitSwitches();
			
			if(!Robot.robotBallHandler.isActive()) done = true;
		}
	}

	protected boolean isFinished() {
		return isTimedOut() || done;
	}

	protected void end() {
		Robot.robotBallHandler.stopRelay();
	}

	protected void interrupted() {
		end();
	}
}