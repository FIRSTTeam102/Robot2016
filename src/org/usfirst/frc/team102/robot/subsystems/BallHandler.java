package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallHandler extends Subsystem {
	
	Relay rl;
	
	public static boolean direction = true;
	
	public BallHandler() { rl = new Relay(RobotMap.relay1); }
	protected void initDefaultCommand() {}
	public void setRelay(boolean direction) { rl.set(direction ? Value.kForward : Value.kReverse); }
	public void stop() { rl.set(Value.kOff); }
}