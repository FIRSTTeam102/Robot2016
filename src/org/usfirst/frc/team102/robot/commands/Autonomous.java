package org.usfirst.frc.team102.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous Mode Command Group
 */
public class Autonomous extends CommandGroup {
	
	public static char inputDataValue;
	
	public static int initialPosition;
	public static Defense[] defs;
	
	public  Autonomous() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		//      addSequential(new Command2());
		// these will run in order.
		
		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		//      addSequential(new Command2());
		// Command1 and Command2 will run in parallel.
		
		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		
		//Actual Autonomous
		//addSequential(new GetInitialData());
		
		//Test Stuff
		addSequential(new DriveStraightNoGyro());
		
	}
	
	public static enum Defense {
		lowBar,
		porticullus, chevalDeFris,
		moat, ramparts,
		drawbridge, sallyPort,
		rockWall, roughTerrain;
		
		/* public Command getPassingCommand() {
		 * 	if(this == lowBar || this == moat || this == ramparts || this == rockWall || this == roughTerrain) ;
		 * 	if(this == porticullus) ;
		 * 	if(this == chevalDeFris) ;
		 * 	if(this == drawbridge) ;
		 *	if(this == sallyPort) ;
		 *	
		 *	return null;
		 *}
		*/
	}
}