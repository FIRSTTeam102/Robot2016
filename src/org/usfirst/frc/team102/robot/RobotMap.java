package org.usfirst.frc.team102.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	// Are we on the test bed?
	public static final boolean isTestBed = true;
	// Do we have USB Cameras?
	public static final boolean hasUSBCams = false;
	
	// USB camera IDs
	public static final int frontCameraID = 1;
	public static final int backCameraID = 3;
	
	// Joysticks
	public static final int driverJoystickPort = 0;
	public static final int operatorJoystickPort = 1;
	public static final int testJoystickPort = 2;

	// Joystick Axis's
	public static final int xBoxLeftXAxis = 0;
	public static final int xBoxLeftYAxis = 1;
	public static final int xBoxRightXAxis = 4;
	public static final int xBoxRightYAxis = 5;

	// Button Indexes
	public static final int xBoxAIndex = 1;
	public static final int xBoxBIndex = 2;
	public static final int xBoxXIndex = 3;
	public static final int xBoxYIndex = 4;
	public static final int xBoxLeftBumperIndex = 5;
	public static final int xBoxRightBumperIndex = 6;
	public static final int xBoxBackButtonIndex = 7;
	public static final int xBoxStartButtonIndex = 8;
	public static final int xBoxLeftJoystickPress = 9;
	public static final int xBoxRightJoystickPress = 10;

	// PWMs
	public static final int backRightMotor1 = 1;
	public static final int backLeftMotor2 = 2;
	public static final int frontRightMotor3 = 3;
	public static final int frontLeftMotor4 = 4;
	public static final int armMotor5 = 5;
	public static final int testBedRealMotor = 10;
	// public static final int m6 = 6;
	// public static final int m7 = 7;

	// Analog Inputs
	// Distance Sensors
	public static final int distanceSensorIndex = 0;
	// The Gyro
	public static final int theGryoAnalogID = 1;

	//Digital Inputs/Outputs
	// Switch Digital Inputs
	public static final int autoSwitch0 = 0;
	public static final int autoSwitch1 = 1;
	public static final int autoSwitch2 = 2;
	public static final int autoSwitch3 = 3;
	// Momentary switches to disable arm movement
	public static final int armLimitSensorTop = 4;
	public static final int armLimitSensorBottom = 5;
	// Momentary switches to disable hoop movement
	public static final int hoopLimitSensorTop = 6;
	public static final int hoopLimitSensorBottom = 7;
	
	// Analog Inputs
	// (Nolonger used) Hardware LED reverse vs. forward indicators
	//public static final int forwardIndicator = 4;
	//public static final int backwardIndicator = 5;

	// Servo Motor
	public static final int xAxisServo = 0;
	public static final int yAxisServo = 1;

	// Relay
	public static final int relay1 = 0;
}