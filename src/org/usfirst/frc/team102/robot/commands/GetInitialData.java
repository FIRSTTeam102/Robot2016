package org.usfirst.frc.team102.robot.commands;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.Autonomous.Def;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Command;
import static org.usfirst.frc.team102.robot.commands.Autonomous.Def.*;

public class GetInitialData extends Command {

	public static boolean lowBar = false;
	public static boolean noAuto = false;
	public static int startingPos = 1;
	public static Def def = null;
	public static boolean turn1, turn2;

	protected void initialize() {
		int i = 0;
		DigitalInput in = new DigitalInput(RobotMap.cfgSw0);
		if (in.get())
			i++;

		in = new DigitalInput(RobotMap.cfgSw1);
		if (in.get())
			i += 2;

		in = new DigitalInput(RobotMap.cfgSw2);
		if (in.get())
			i += 4;

		in = new DigitalInput(RobotMap.cfgSw3);
		if (in.get())
			i += 8;

		in = null;

		if (i == 0) {
			noAuto = true;
		}
		if (i == 1) {
			lowBar = true;
			turn1 = true;
		}
		if (i == 2) {
			startingPos = 2;
			def = B;
			turn1 = true;
		}
		if (i == 3) {
			startingPos = 2;
			def = D;
			turn1 = true;
		}
		if (i == 4) {
			startingPos = 3;
			def = B;
		}
		if (i == 5) {
			startingPos = 3;
			def = D;
		}
		if (i == 6) {
			startingPos = 4;
			def = B;
			turn2 = true;
		}
		if (i == 7) {
			startingPos = 4;
			def = D;
			turn2 = true;
		}
		if (i == 8) {
			startingPos = 5;
			def = B;
			turn2 = true;
		}
		if (i == 9) {
			startingPos = 5;
			def = D;
			turn2 = true;
		}
		if (i == 10)
			;
		if (i == 11)
			;
		if (i == 12)
			;
		if (i == 13)
			;
		if (i == 14)
			;
		if (i == 15)
			;
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}