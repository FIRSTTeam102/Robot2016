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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallHandler extends Subsystem {

	public static boolean dir;

	private Relay r1;
	private DigitalInput limitSensorTop, limitSensorBottom;
	
	private Talon handleBallMotor;
	
	public BallHandler() {
		r1 = new Relay(RobotMap.relay1, Direction.kBoth);
		
		limitSensorTop = new DigitalInput(RobotMap.hoopLimitSensorTop);
		limitSensorBottom = new DigitalInput(RobotMap.hoopLimitSensorBottom);
		
		handleBallMotor = new Talon(RobotMap.hoopTalon);
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
	
	@SuppressWarnings("unused")
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
	
	public void handleBall(boolean direction) {
		handleBallMotor.set(direction ? -1 : 1);
	}
	
	public void stopTalon() {
		handleBallMotor.set(0);
	}
	
	public void updateDashboard(){
		
		if(!limitSensorTop.get())
			SmartDashboard.putString("DB/String 0", "BH: TOP ON");
		else if(!limitSensorBottom.get())
			SmartDashboard.putString("DB/String 0", "BH: BOTTOM ON");
		else
			SmartDashboard.putString("DB/String 0", "BH: ");
	}
}