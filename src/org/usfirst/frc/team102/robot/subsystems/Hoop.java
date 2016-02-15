package org.usfirst.frc.team102.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Hoop extends Subsystem {

	public static boolean dir;

	private Relay r1;

	public Hoop() {
		r1 = new Relay(RobotMap.relay1);
	}

	protected void initDefaultCommand() {
	}

	public void setRelay(boolean direction) {
		
		
		if (direction = true){
			
			r1.set(Value.kForward);
			
		}else if (direction = false){
			r1.set(Value.kReverse);
		}
		
	}

	public void stop() {
		r1.set(Value.kOff);
	}
}