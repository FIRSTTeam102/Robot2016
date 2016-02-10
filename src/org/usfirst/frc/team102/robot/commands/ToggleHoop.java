package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleHoop extends Command {

	private final boolean dir;

	public ToggleHoop(boolean direction) {
		requires(Robot.robotRelay);
		setTimeout(.125);

		dir = direction;
	}

	protected void initialize() {
		Robot.robotRelay.setRelay(dir);
	}

	protected void execute() {

	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Robot.robotRelay.stop();
	}

	protected void interrupted() {
		end();
	}
}