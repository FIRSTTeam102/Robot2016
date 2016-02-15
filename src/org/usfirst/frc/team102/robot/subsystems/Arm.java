package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.Robot;
import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.Rumbler.Rumbles;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	CANTalon armMotor;
	private DigitalInput limitSensorTop, limitSensorBottom;
	private double maxNominalCurrent = 24.5;

	public Arm() {
		armMotor = new CANTalon(RobotMap.armMotor5);

		limitSensorTop = new DigitalInput(RobotMap.limitSensorTop);
		limitSensorBottom = new DigitalInput(RobotMap.limitSensorBottom);
	}

	public void initDefaultCommand() {
	}

	public void startArm(double speed) {
		//System.out.println(limitSensorTop.get() + " : " + limitSensorBottom.get());
		// System.out.println("Output current: " + armMotor.getOutputCurrent());
		//
		// if (armMotor.getOutputCurrent() > maxNominalCurrent) { // Arm has
		// // probably hit
		// // the floor.
		// Robot.oi.opRumble.playRumbleMessage(Rumbles.alert);
		// stopArm();
		// return;
		// }
		//
//		if (speed > 0 && !limitSensorTop.get()) {
//			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
//			stopArm();
//			return;
//		}
//
//		if (speed < 0 && !limitSensorBottom.get()) {
//			Robot.oi.opRumble.playRumbleMessage(Rumbles.error);
//			stopArm();
//			return;
//		}

		armMotor.set(speed);
	}

	public void stopArm() {
		armMotor.set(0.0);
	}
}