package org.usfirst.frc.team102.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hoop extends Subsystem {

	public static boolean dir;

	private Relay r1;
	private DigitalInput limitSensorTop, limitSensorBottom;
	
	private Talon tal1;
	
	public Hoop() {
		r1 = new Relay(RobotMap.relay1, Direction.kBoth);
		
		limitSensorTop = new DigitalInput(RobotMap.hoopLimitSensorTop);
		limitSensorBottom = new DigitalInput(RobotMap.hoopLimitSensorBottom);
		
		tal1 = new Talon(RobotMap.hoopTalon);
	}

	protected void initDefaultCommand() {
	}
	
	public boolean isActive() {
		return r1.get() != Value.kOff;
	}
	
	public void checkLimitSwitches() {
		if (r1.get() == Value.kForward && !limitSensorTop.get()) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
			r1.set(Value.kOff);
			return;
		}

		if (r1.get() == Value.kReverse && !limitSensorBottom.get()) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
			r1.set(Value.kOff);
			return;
		}
	}
	
	public void setRelay(boolean direction) {
		if (direction && !limitSensorTop.get() && !RobotMap.isTestBed) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
			r1.set(Value.kOff);
			return;
		}

		if (!direction && !limitSensorBottom.get() && !RobotMap.isTestBed) {
			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
			r1.set(Value.kOff);
			return;
		}
		
		if(direction) {
			r1.set(Value.kForward);
		} else {
			r1.set(Value.kReverse);
		}
	}

	public void stopRelay() {
		r1.set(Value.kOff);
	}
	
	public void setTalon(boolean direction) {
		tal1.set(direction ? -1 : 1);
	}
	
	public void stopTalon() {
		tal1.set(0);
	}
}