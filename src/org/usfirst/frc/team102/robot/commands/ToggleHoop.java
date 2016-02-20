package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleHoop extends Command {

	private final boolean dir;

	public ToggleHoop(boolean direction) {
		requires(Robot.robotHoop);
		setTimeout(.25);

		dir = direction;
	}

	protected void initialize() {
		Robot.robotHoop.setRelay(dir);
	}

	protected void execute() {
		if(!RobotMap.isTestBed) Robot.robotHoop.checkLimitSwitches();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.robotHoop.stop();
	}

	protected void interrupted() {
		end();
	}
}