package org.usfirst.frc.team102.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hoop extends Subsystem {

	public static boolean dir;

	Relay rl;

	public Hoop() {
		rl = new Relay(RobotMap.relay1);
		dir = (rl.get() == Value.kForward);
	}

	protected void initDefaultCommand() {
	}

	public void setRelay(boolean direction) {
		rl.set(direction ? Value.kForward : Value.kReverse);
	}

	public void stop() {
		rl.set(Value.kOff);
	}
}