package org.usfirst.frc.team102.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int driverJoystickPort = 0;
	public static final int operatorJoystickPort = 1;
	public static final int testJoystickPort = 2;
	public static final int xBoxLeftXAxis = 0;
	public static final int xBoxLeftYAxis = 1;
	public static final int xBoxRightXAxis = 4;
	public static final int xBoxRightYAxis = 5;
	public static final int m1 = 0;
	public static final int m2 = 1;
	public static final int m3 = 3;
	public static final int m4 = 4;
	public static final int m5 = 5;
	public static final int m6 = 6;
	public static final int m7 = 7;
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
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static int forwardIndicator = 0;
	public static int backwardIndicator = 1;
	
	public static int cfgSw0 = 2, cfgSw1 = 3, cfgSw2 = 4, cfgSw3 = 5, cfgSw4 = 6, cfgSw5 = 7, cfgSw6 = 8, cfgSw7 = 9;
	
	public static int xAxisServo = 0, yAxisServo = 1;
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
