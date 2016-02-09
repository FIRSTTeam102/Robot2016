package org.usfirst.frc.team102.robot;

import org.usfirst.frc.team102.robot.commands.CommandToggleReverse;
import org.usfirst.frc.team102.robot.commands.Dancing;
import org.usfirst.frc.team102.robot.commands.MoveArm;
import org.usfirst.frc.team102.robot.commands.MoveArmOpposite;
import org.usfirst.frc.team102.robot.commands.MoveBall;
import org.usfirst.frc.team102.robot.commands.Whip;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	private Joystick xBoxDriver;
	private Joystick xBoxOperator;
	private Joystick xBoxTest;
	private JoystickButton xBoxA;
	private JoystickButton xBoxY;
	//private JoystickButton xBoxB;
	private JoystickButton xBoxX;
	//private JoystickButton xBoxRightBump;
	//private JoystickButton xBoxLeftBump;
	private JoystickButton xBoxStart;
	private JoystickButton xBoxOpStart;
	private JoystickButton xBoxOpBack;
	public static Rumbler driveRumble, opRumble, testRumble;
	
	public OI() {
		xBoxDriver = new Joystick(RobotMap.driverJoystickPort);
		xBoxOperator = new Joystick(RobotMap.operatorJoystickPort);
		xBoxTest = new Joystick(RobotMap.testJoystickPort);
		
		driveRumble = new Rumbler(xBoxDriver);
		opRumble = new Rumbler(xBoxOperator);
		testRumble = new Rumbler(xBoxTest);
		
		xBoxA = new JoystickButton(xBoxOperator, RobotMap.xBoxAIndex);
		xBoxA.whenPressed(new MoveArmOpposite(0.6, 1));
		xBoxA.whenReleased(new MoveArmOpposite(0.0, 1));

		xBoxY = new JoystickButton(xBoxOperator, RobotMap.xBoxYIndex);
		xBoxY.whenPressed(new MoveArm(0.6, 1));
		xBoxY.whenReleased(new MoveArm(0.0, 1));

		//xBoxB = new JoystickButton(xBoxOperator, RobotMap.xBoxBIndex);
		//xBoxB.whenPressed(new MoveArm(0.6, 2)); // These arms currently don't exist, and
		//xBoxB.whenReleased(new MoveArm(0.0, 2)); // probably never will.
		
		// Now used to get the ball. // Never mind, it is now one command.
		// xBoxB.whenPressed(new GetBall());
		
		xBoxX = new JoystickButton(xBoxOperator, RobotMap.xBoxXIndex);
		//xBoxX.whenPressed(new MoveArmOpposite(0.6, 2)); // These arms don't currently don't exist, and
		//xBoxX.whenReleased(new MoveArmOpposite(0.0, 2)); // probably never will.
		
		// Now used to shoot the ball.
		xBoxX.whenPressed(new MoveBall());
		
		//xBoxRightBump = new JoystickButton(xBoxOperator, RobotMap.xBoxRightBumperIndex);
		//xBoxRightBump.whenPressed(new MoveArmOpposite(0.6, 3)); // These arms currently don't exist, and
		//xBoxRightBump.whenReleased(new MoveArmOpposite(0.0, 3)); // probably never will.
		
		//xBoxLeftBump = new JoystickButton(xBoxOperator, RobotMap.xBoxLeftBumperIndex);
		//xBoxLeftBump.whenPressed(new MoveArm(0.6, 3)); // These arms currently don't exist, and
		//xBoxLeftBump.whenReleased(new MoveArm(0.0, 3)); // probably never will.
		
		xBoxA = new JoystickButton(xBoxDriver, RobotMap.xBoxAIndex);
		
		xBoxOpStart = new JoystickButton(xBoxOperator, RobotMap.xBoxStartButtonIndex);
		xBoxOpStart.whenPressed(new Dancing(.5));
		
		xBoxOpBack = new JoystickButton(xBoxOperator, RobotMap.xBoxBackButtonIndex);
		xBoxOpBack.whenPressed(new Whip());
		
		// THIS IS COMMENTED FOR RUMBLER TEST MODE.
		// Uncomment the next two lines and comment the line after
		// to disable this.
		//xBoxA.whenPressed(new CommandRumble(xBoxDriver, Rumbles.L1R1));
		
		xBoxStart = new JoystickButton(xBoxDriver, RobotMap.xBoxStartButtonIndex);
		xBoxStart.whenPressed(new CommandToggleReverse());
	}
	
	public Joystick getDriverXBox() { 
		return xBoxDriver; 
		}
	public Joystick getOperatorXBox() { 
		return xBoxOperator; 
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
