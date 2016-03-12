package org.usfirst.frc.team102.robot.subsystems;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.DriveWithXBox;

import team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {

	public CANTalon m1;
	public CANTalon m2;
	public CANTalon m3;
	public CANTalon m4;
	// private DigitalOutput isGoingForward, isGoingBackward; // We are now
	// using outputs on the Driver station
	private double leftJoyX;
	private double leftJoyY;
	private double rightJoyX;
	private double rightJoyY;
	public final double autoModeSpeed = .5;
	public boolean isReverse;
	private AnalogInput distanceSensor;
	public AnalogGyro theGyro;
	private double gyroVal;
	private double desiredGyroMeasure;
	public boolean isDoneTurning = false;
	private double encoderZeroPos = 0;
	public BufferedWriter logWriter = null;
	public String SimAutoInfoFile = "SensorData.txt";
	Scanner simAutoInfoFile = null;
	File logFile = null;
	String strGyroReading = " ";

	// WARNING: This array has TONZ of pre-calculated values for the distance
	// sensor.
	private int[] distanceSensorValues = { 136, 136, 136, 136, 135, 135, 135, 135, 135, 135, 134, 134, 134, 134, 134,
			134, 133, 133, 133, 133, 133, 133, 132, 132, 132, 132, 132, 132, 131, 131, 131, 131, 131, 130, 130, 130,
			130, 130, 130, 129, 129, 129, 129, 129, 129, 129, 128, 128, 128, 128, 128, 128, 127, 127, 127, 127, 127,
			127, 126, 126, 126, 126, 126, 126, 125, 125, 125, 125, 125, 125, 124, 124, 124, 124, 124, 124, 123, 123,
			123, 123, 123, 123, 123, 122, 122, 122, 122, 122, 122, 121, 121, 121, 121, 121, 121, 120, 120, 120, 120,
			120, 120, 120, 119, 119, 119, 119, 119, 119, 118, 118, 118, 118, 118, 118, 118, 117, 117, 117, 117, 117,
			117, 117, 116, 116, 116, 116, 116, 116, 115, 115, 115, 115, 115, 115, 115, 114, 114, 114, 114, 114, 114,
			114, 113, 113, 113, 113, 113, 113, 113, 112, 112, 112, 112, 112, 112, 112, 111, 111, 111, 111, 111, 111,
			111, 110, 110, 110, 110, 110, 110, 110, 109, 109, 109, 109, 109, 109, 109, 108, 108, 108, 108, 108, 108,
			108, 107, 107, 107, 107, 107, 107, 107, 106, 106, 106, 106, 106, 106, 106, 106, 105, 105, 105, 105, 105,
			105, 105, 104, 104, 104, 104, 104, 104, 104, 104, 103, 103, 103, 103, 103, 103, 103, 102, 102, 102, 102,
			102, 102, 102, 102, 101, 101, 101, 101, 101, 101, 101, 101, 100, 100, 100, 100, 100, 100, 100, 99, 99, 99,
			99, 99, 99, 99, 99, 98, 98, 98, 98, 98, 98, 98, 98, 97, 97, 97, 97, 97, 97, 97, 97, 96, 96, 96, 96, 96, 96,
			96, 96, 95, 95, 95, 95, 95, 95, 95, 95, 94, 94, 94, 94, 94, 94, 94, 94, 94, 93, 93, 93, 93, 93, 93, 93, 93,
			92, 92, 92, 92, 92, 92, 92, 92, 92, 91, 91, 91, 91, 91, 91, 91, 91, 90, 90, 90, 90, 90, 90, 90, 90, 90, 89,
			89, 89, 89, 89, 89, 89, 89, 88, 88, 88, 88, 88, 88, 88, 88, 88, 87, 87, 87, 87, 87, 87, 87, 87, 87, 86, 86,
			86, 86, 86, 86, 86, 86, 86, 85, 85, 85, 85, 85, 85, 85, 85, 85, 84, 84, 84, 84, 84, 84, 84, 84, 84, 84, 83,
			83, 83, 83, 83, 83, 83, 83, 83, 82, 82, 82, 82, 82, 82, 82, 82, 82, 81, 81, 81, 81, 81, 81, 81, 81, 81, 81,
			80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 79, 79, 79, 79, 79, 79, 79, 79, 79, 79, 78, 78, 78, 78, 78, 78, 78,
			78, 78, 78, 77, 77, 77, 77, 77, 77, 77, 77, 77, 77, 76, 76, 76, 76, 76, 76, 76, 76, 76, 76, 75, 75, 75, 75,
			75, 75, 75, 75, 75, 75, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 74, 73, 73, 73, 73, 73, 73, 73, 73, 73, 73,
			72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 72, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 70, 70, 70, 70, 70,
			70, 70, 70, 70, 70, 70, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 69, 68, 68, 68, 68, 68, 68, 68, 68, 68,
			68, 68, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 67, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 66, 65, 65,
			65, 65, 65, 65, 65, 65, 65, 65, 65, 65, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 64, 63, 63, 63, 63,
			63, 63, 63, 63, 63, 63, 63, 63, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 62, 61, 61, 61, 61, 61, 61, 61,
			61, 61, 61, 61, 61, 61, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 59, 59, 59, 59, 59, 59, 59, 59,
			59, 59, 59, 59, 59, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 58, 57, 57, 57, 57, 57, 57, 57, 57,
			57, 57, 57, 57, 57, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 56, 55, 55, 55, 55, 55, 55, 55, 55,
			55, 55, 55, 55, 55, 55, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 54, 53, 53, 53, 53, 53, 53,
			53, 53, 53, 53, 53, 53, 53, 53, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 52, 51, 51, 51, 51,
			51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,
			49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 49, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48, 48,
			48, 48, 48, 48, 48, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 47, 46, 46, 46, 46, 46,
			46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 46, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45, 45,
			45, 45, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 44, 43, 43, 43, 43, 43, 43, 43,
			43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 43, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42,
			42, 42, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 41, 40, 40, 40, 40, 40, 40,
			40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39, 39,
			39, 39, 39, 39, 39, 39, 39, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38, 38,
			37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 36, 36, 36, 36, 36, 36,
			36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 36, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35,
			35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 35, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34, 34,
			34, 34, 34, 34, 34, 34, 34, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33, 33,
			33, 33, 33, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32,
			32, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 31, 30,
			30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 30, 29, 29,
			29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 29, 28, 28, 28,
			28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 28, 27, 27,
			27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27, 27,
			26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26, 26,
			26, 26, 26, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25, 25,
			25, 25, 25, 25, 25, 25, 25, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24,
			24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 24, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23,
			23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 23, 22, 22, 22, 22, 22, 22, 22, 22,
			22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22, 22,
			21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 21,
			21, 21, 21, 21, 21, 21, 21, 21, 21, 21, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20,
			20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 19, 19, 19, 19, 19,
			19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
			19, 19, 19, 19, 19, 19, 19, 19, 19, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
			18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 17, 17,
			17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17,
			17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
			16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,
			16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
			15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,
			15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
			14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14,
			14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
			13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13,
			13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
			12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
			12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12,
			12, 11, 11, 11 };

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithXBox());
	}

	public DriveTrain(double p, double i, double d) {
		super(p, i, d);
		m1 = new CANTalon(RobotMap.backRightMotor1);
		m2 = new CANTalon(RobotMap.backLeftMotor2);
		m3 = new CANTalon(RobotMap.frontRightMotor3);

		if (RobotMap.isTestBed) {
			m4 = new CANTalon(RobotMap.testBedRealMotor);
		} else {
			m4 = new CANTalon(RobotMap.frontLeftMotor4);
		}

		// isGoingForward = new DigitalOutput(RobotMap.forwardIndicator);
		// isGoingBackward = new DigitalOutput(RobotMap.backwardIndicator);

		// populateDistancesArray();
		// Use the method described in Notes.txt to calculate the values if you
		// wish.

		// This is the line that causes segfaults on the test bed
		// AnalogInput.setGlobalSampleRate(62500);
		// Crash happens in getAnalogNumActiveChannels

		theGyro = new AnalogGyro(RobotMap.theGryoAnalogID);
		theGyro.initGyro();
		theGyro.calibrate();

		// Encoders:
		// Left = Motor 4
		// Right = Motor 3

		resetEncoder();
	}

	public void driveWithXBox(Joystick xBox) {
		// isGoingForward.set(!isReverse);
		// isGoingBackward.set(isReverse);

		// Reverse indicator on the Driver Station, as requested.
		SmartDashboard.putBoolean("DB/LED 0", isReverse); // FIXME

		/*
		 * if (getEncoderValue() >= 5000) resetEncoder();
		 * System.out.println(getEncoderValue());
		 */

		// System.out.println(getEncoderInches());

		try {
			leftJoyX = xBox.getRawAxis(RobotMap.xBoxLeftXAxis);
			leftJoyY = xBox.getRawAxis(RobotMap.xBoxLeftYAxis);
			rightJoyX = xBox.getRawAxis(RobotMap.xBoxRightXAxis);
			rightJoyY = xBox.getRawAxis(RobotMap.xBoxRightYAxis);

			leftJoyX *= 1.0;
			leftJoyY *= 1.0;
			rightJoyX *= 1.0;
			rightJoyY *= 1.0;

			if (Math.abs(leftJoyX) < 0.1)
				leftJoyX = 0.0;
			if (Math.abs(leftJoyY) < 0.1)
				leftJoyY = 0.0;
			if (Math.abs(rightJoyX) < 0.1)
				rightJoyX = 0.0;
			if (Math.abs(rightJoyY) < 0.1)
				rightJoyY = 0.0;

			if (isReverse) {
				m1.set(-leftJoyY);
				m3.set(-leftJoyY);
				m2.set(rightJoyY);
				m4.set(rightJoyY);
			} else {
				m1.set(rightJoyY);
				m3.set(rightJoyY);
				m2.set(-leftJoyY);
				m4.set(-leftJoyY);
			}
		} catch (Exception ex1) {
			ex1.printStackTrace();
			DriverStation.reportError(ex1.getMessage(), true);
		}
	}

	public void danceDriveLeft(double speed) {
		m2.set(speed);
		m4.set(speed);
	}

	public void danceDriveRight(double speed) {
		m1.set(-speed);
		m3.set(-speed);
	}

	// ========== Begin autonomous mode handling code ==========

	public void startDriving(boolean isReverse) {
		m1.set(isReverse ? -autoModeSpeed : autoModeSpeed);
		m3.set(isReverse ? -autoModeSpeed : autoModeSpeed);
		m2.set(!isReverse ? -autoModeSpeed : autoModeSpeed);
		m4.set(!isReverse ? -autoModeSpeed : autoModeSpeed);
	}

	public void startTurning(boolean direction) {
		m1.set(direction ? -autoModeSpeed : autoModeSpeed);
		m3.set(direction ? -autoModeSpeed : autoModeSpeed);
		m2.set(direction ? -autoModeSpeed : autoModeSpeed);
		m4.set(direction ? -autoModeSpeed : autoModeSpeed);
	}

	public void stop() {
		m1.set(0);
		m2.set(0);
		m3.set(0);
		m4.set(0);
	}

	// End auto-mode handling code

	public int getDistanceSensorRawInput() {
		if (!RobotMap.hasDistanceSensor)
			return -1;

		if (distanceSensor == null)
			distanceSensor = new AnalogInput(RobotMap.distanceSensorIndex);

		return distanceSensor.getValue();
	}

	// returns: distance reading from distance sensor in centimeters
	public int getModulatedDistance() {
		if (!RobotMap.hasDistanceSensor)
			return -1;

		int raw = getDistanceSensorRawInput();
		// System.out.println(raw);

		if (raw > 2253 || raw < 328) {
			/*
			 * DriverStation.reportError(
			 * "Warning: Distance sensor output is out of the expected range. (Check callibration??)"
			 * , false);
			 */
			return -1;
		}

		// Distance sensor units are in CM, converted to IN by dividing by 2.54
		return (int) Math.round(distanceSensorValues[raw - 328] / 2.54);
	}

	protected double returnPIDInput() {
		return gyroVal = (theGyro.getAngle() % 360);
	}

	protected void usePIDOutput(double output) {
		output = Math.round(output);
		gyroVal = Math.round(gyroVal);

		// if (RobotMap.isTestBed)
		// System.out.println("Gyro: " + gyroVal + ", Out: " + output + ",
		// Desired Angle: " + desiredGyroMeasure
		// + ", Encoder (Inches): " + getEncoderInches());
		// System.out.println("Encoder (Position): " + getEncoderPos());

		if (gyroVal == desiredGyroMeasure && gyroVal != 0) {
			theGyro.reset();
			setSetpoint(desiredGyroMeasure = 0);
			disable();
			isDoneTurning = true;
		}

		if (desiredGyroMeasure == 0) {
			m1.set(autoModeSpeed + output);
			m2.set(-autoModeSpeed + output);
			m3.set(autoModeSpeed + output);
			m4.set(-autoModeSpeed + output);
		} else {
			m1.set(output);
			m2.set(-output);
			m3.set(output);
			m4.set(-output);
		}
	}

	public void setDriveStraight() {
		desiredGyroMeasure = 0;
		theGyro.reset();
		enable();
		// startDriving(false);

		setSetpoint(desiredGyroMeasure);
	}

	/**
	 * @param degrees
	 *            Degrees relative to CURRENT POSITION
	 */
	public void setTurnToAngle(double degrees) {
		desiredGyroMeasure = degrees;
		theGyro.reset();
		enable();
		isDoneTurning = false;
		setSetpoint(desiredGyroMeasure);
	}

	private double getEncoderValue() {
		int val;
		// Math.abs(m3.getEncPosition()) + Math.abs(m4.getEncPosition()) / 2; //
		// Enodia
		// Math.abs(m4.getEncPosition()); // Test bed

		if (RobotMap.isTestBed) {
			val = Math.abs(m4.getEncPosition());
		} else {
			val = Math.abs(m3.getEncPosition()) + Math.abs(m4.getEncPosition()) / 2;
		}

		return Math.abs(val);
	}

	public int getEncoderPos() {
		return (int) (Math.round(getEncoderValue()) - encoderZeroPos);
	}

	public void resetEncoder() {
		encoderZeroPos = getEncoderValue();
	}

	public int getEncoderInches() {
		// Empirically, 1 rotation ~= 1400 encoder ticks
		// 1 turn ~= 10 inches
		return (int) ((Math.round(getEncoderValue()) - encoderZeroPos) / 140);
	}

	public void getInfo() {
		double distanceSensorReading = -1;
		double gyroReading = -1;
		double encoderReading = -1;
//		if (RobotMap.isTestBed) {
//			if (simAutoInfoFile != null) {
//				 if (simAutoInfoFile.hasNextInt()){
//					 distanceSensorReading = getModulatedDistance();
//				 	 gyroReading = theGyro.getAngle();
//				 	 encoderReading = getEncoderValue();
//				 }
//			}
//		} else {
			distanceSensorReading = getModulatedDistance();
			gyroReading = theGyro.getAngle();
		 	encoderReading = getEncoderInches();
		 	strGyroReading = String.format("%1.6s", gyroReading);
//		}
			try {
				 logWriter.write(String.format("\n" + Timer.getFPGATimestamp() + "\t" + distanceSensorReading + "\t" + strGyroReading + 
						 "\t" + encoderReading));
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void setUpAutoInfo() {
//		if (RobotMap.isTestBed) {
//			try {
//				String simFile = "/home/lvuser/SimDist/" + SimAutoInfoFile;
//				MessageLogger.LogMessage("Opening simulation file: " + simFile);
//				simAutoInfoFile = new Scanner(new File(simFile));
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}

		// Open a file for logging the distance sensor output.
		try {
			// create a temporary file
			String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			logFile = new File("/home/lvuser/" + timeLog + ".log");
			logWriter = new BufferedWriter(new FileWriter(logFile));
			logWriter.write("TimeStamp:\t\tds:\t\tgyro:\tenc.(in.):");
			System.out.println("Created log writer.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeFile() {
		try {
			// Close the log file.
			logWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}