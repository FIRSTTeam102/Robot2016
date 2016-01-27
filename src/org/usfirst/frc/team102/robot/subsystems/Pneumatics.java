package org.usfirst.frc.team102.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
    
    public static Solenoid sol1 = new Solenoid(1);
    public static Solenoid sol2 = new Solenoid(2);
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public static void setSolenoid(boolean turnOn){
    	if(turnOn){
    		
        	sol1.set(true);
        	
    	}else{
    		
    		sol1.set(false);
    	}

    }
}

