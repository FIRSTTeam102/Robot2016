package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Victor armMotor1;
	Victor armMotor2;
	Jaguar armMotor3;

	public Arm() {
		armMotor1 = new Victor(RobotMap.m3);
		armMotor2 = new Victor(RobotMap.m4);
		armMotor3 = new Jaguar(RobotMap.m5);
	}

	public void initDefaultCommand() {
	}

	public void startArm(double speed, int armNumber) {
		if (armNumber == 1) {
			armMotor1.set(speed);
		} else if (armNumber == 2) {
			armMotor2.set(speed);
		} else if (armNumber == 3) {
			armMotor3.set(speed);
		}
	}

	public void stopArm() {
		armMotor1.set(0.0);
	}
}