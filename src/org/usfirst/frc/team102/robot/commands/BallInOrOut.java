package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class BallInOrOut extends Command {

	public boolean direction;

	public BallInOrOut(boolean direction) {
		requires(Robot.robotHoop);
		this.direction = direction;
	}

	protected void initialize() {
		Robot.robotHoop.handleBall(direction);
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