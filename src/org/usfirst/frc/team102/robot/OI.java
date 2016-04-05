package org.usfirst.frc.team102.robot;

import org.usfirst.frc.team102.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick xBoxDriver;
	private Joystick xBoxOperator;
	private Joystick xBoxTester;
	private JoystickButton xBoxA;
	private JoystickButton xBoxY;
	private JoystickButton xBoxB;
	private JoystickButton xBoxX;
	private JoystickButton xBoxRightBump;
	private JoystickButton xBoxLeftBump;
	private JoystickButton xBoxStart;
	private JoystickButton xBoxOpStart;
	private JoystickButton xBoxOpBack;
	private JoystickButton xBoxTestA;
	private JoystickButton xBoxTestB;
	@SuppressWarnings("unused")
	private JoystickButton xBoxTestX;
	@SuppressWarnings("unused")
	private JoystickButton xBoxTestY;
	public Rumbler driveRumble, opRumble, testRumble;

	public final boolean enableTestJoystick = false;

	public OI() {
		xBoxDriver = new Joystick(RobotMap.driverJoystickPort);
		xBoxOperator = new Joystick(RobotMap.operatorJoystickPort);
		if (enableTestJoystick)
			xBoxTester = new Joystick(RobotMap.testJoystickPort);

		driveRumble = new Rumbler(xBoxDriver);
		opRumble = new Rumbler(xBoxOperator);
		
		if (enableTestJoystick)
			testRumble = new Rumbler(xBoxTester);

		//Operator Joystick Commands
		xBoxY = new JoystickButton(xBoxOperator, RobotMap.xBoxYIndex);
		xBoxY.whenPressed(new MoveArmOpposite(0.75));
		xBoxY.whenReleased(new MoveArmOpposite(0.0));

		xBoxA = new JoystickButton(xBoxOperator, RobotMap.xBoxAIndex);
		xBoxA.whenPressed(new MoveArm(0.75));
		xBoxA.whenReleased(new MoveArm(0.0));

		xBoxB = new JoystickButton(xBoxOperator, RobotMap.xBoxBIndex);
	//	xBoxB.whenPressed(new MoveHoop(true));
	//	xBoxB.whenReleased(new StopHoop());
		xBoxB.whenPressed(new BallInOrOut(true));
		xBoxB.whenReleased(new StopBall());
		
		xBoxX = new JoystickButton(xBoxOperator, RobotMap.xBoxXIndex);
	//	xBoxX.whenPressed(new MoveHoop(false));
	//	xBoxX.whenReleased(new StopHoop());
		xBoxX.whenPressed(new BallInOrOut(false));
		xBoxX.whenReleased(new StopBall());

		xBoxRightBump = new JoystickButton(xBoxOperator, RobotMap.xBoxRightBumperIndex);
		
		if(RobotMap.hasBallHandlerRelay) {
			xBoxRightBump.whenPressed(new MoveGrabberManual(true));
			xBoxRightBump.whenReleased(new StopGrabber());
		}
		
		xBoxLeftBump = new JoystickButton(xBoxOperator, RobotMap.xBoxLeftBumperIndex);
		
		if(RobotMap.hasBallHandlerRelay) {
			xBoxLeftBump.whenPressed(new MoveGrabberManual(false));
			xBoxLeftBump.whenReleased(new StopGrabber());
		}

		xBoxOpStart = new JoystickButton(xBoxOperator, RobotMap.xBoxStartButtonIndex);
		xBoxOpStart.whenPressed(new Dancing(.5));
//		xBoxOpStart.whenPressed(new MoveGrabberAuto(true));
//		xBoxOpStart.whenReleased(new StopGrabber());

		xBoxOpBack = new JoystickButton(xBoxOperator, RobotMap.xBoxBackButtonIndex);
		xBoxOpBack.whenPressed(new Whip());
//		xBoxOpBack.whenPressed(new MoveGrabberAuto(false));
//		xBoxOpBack.whenReleased(new StopGrabber());
		
		//Driver Joystick commands
		xBoxA = new JoystickButton(xBoxDriver, RobotMap.xBoxAIndex);
		
		xBoxStart = new JoystickButton(xBoxDriver, RobotMap.xBoxStartButtonIndex);
		xBoxStart.whenPressed(new CommandToggleReverse());

		// THIS IS COMMENTED FOR RUMBLER TEST MODE.
		// Uncomment the next two lines and comment the line after
		// to disable this.
		// xBoxA.whenPressed(new CommandRumble(xBoxDriver, Rumbles.L1R1));		

		if (enableTestJoystick) {

			// Test Joystick commands

			xBoxTestA = new JoystickButton(xBoxTester, RobotMap.xBoxAIndex);
			xBoxTestB = new JoystickButton(xBoxTester, RobotMap.xBoxBIndex);
			xBoxTestX = new JoystickButton(xBoxTester, RobotMap.xBoxXIndex);
			xBoxTestY = new JoystickButton(xBoxTester, RobotMap.xBoxYIndex);
			
			if(RobotMap.hasBallHandlerRelay) {
				xBoxTestA.whenPressed(new MoveGrabberManual(true));
				xBoxTestA.whenReleased(new StopGrabber());

				xBoxTestB.whenPressed(new MoveGrabberManual(false));
				xBoxTestB.whenReleased(new StopGrabber());
			}
		}
	}

	public Joystick getDriverXBox() {
		return xBoxDriver;
	}

	public Joystick getOperatorXBox() {
		return xBoxOperator;
	}

	public Joystick getTesterXBox() {
		return xBoxTester;
	}

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
