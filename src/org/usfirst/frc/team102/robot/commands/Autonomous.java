package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous Mode Command Group
 */
public class Autonomous extends CommandGroup {

	public static char inputDataValue;

	public static int initialPosition;

	public static final boolean IS_TEST_MODE = false;
	public static final double AUTO_MODE_SPEED = .5;
	public static final double CROSS_DEF_TIME = 1;

	private static AnalogInput distanceSensor;

	public Autonomous() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		if (IS_TEST_MODE) { // Test Stuff
			DriverStation.reportError("Autonomous Tests activated!", false);

			addSequential(new DriveStraightNoGyro(2));
		} else { // "REAL" auto-mode code
			addSequential(new GetInitialData());
			addSequential(new DriveStraightWithGyro(CROSS_DEF_TIME));
			addSequential(new Turn()); // TODO make sure turn direction is
										// correct
			// addSequential(new ShootBall());
			addSequential(new ToggleHoop(false)); //
		}
	}

	public static enum Def {
		B, D;
	}

	public static int getDistanceSensorRawInput() {
		if (distanceSensor == null)
			distanceSensor = new AnalogInput(RobotMap.distanceSensorIndex);

		return distanceSensor.getValue();
	}

	public static int getModulatedDistance() {
		int raw = getDistanceSensorRawInput();

		if (raw > 550 || raw < 1) {
			DriverStation.reportError(
					"Warning: Distance sensor output is out of the expected range. (Check callibration??)", false);
			return -2;
		}

		int out = -1;

		// Begin really complicated best-fit math-y stuff to calculate a linear
		// distance

		// TODO brain-explode-y math

		// O.K. We're done. You can look without brain explosions now.

		return out;
	}
}