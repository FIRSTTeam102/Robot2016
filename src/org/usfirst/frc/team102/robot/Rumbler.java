package org.usfirst.frc.team102.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;

public class Rumbler {

	private final Joystick js;

	public Rumbler(Joystick js) {
		this.js = js;
	}

	private void setRough(boolean on) {
		js.setRumble(RumbleType.kLeftRumble, on ? 1 : 0);
	}

	private void setSoft(boolean on) {
		js.setRumble(RumbleType.kRightRumble, on ? 1 : 0);
	}

	public void playRumbleMessage(Rumbles type) {
		new Thread(new Runnable() {
			public void run() {
				switch (type) {
				// (Disabled) Test Rumbles
				/*
				 * case L1R1: { doLeftRumble(true); time(1);
				 * doLeftRumble(false); time(1); doRightRumble(true); time(1);
				 * doRightRumble(false);
				 * 
				 * break; }
				 * 
				 * case LR1: { doAllRumble(true); time(1); doAllRumble(false);
				 * 
				 * break; }
				 * 
				 * case L2: { doLeftRumble(true); time(2); doLeftRumble(false);
				 * 
				 * break; }
				 * 
				 * case R2: { doRightRumble(true); time(2);
				 * doRightRumble(false);
				 * 
				 * break; }
				 */

				case forward: { // To Op: When driver sets robot to FORWARD
					setSoft(true);
					time(1);
					setSoft(false);

					break;
				}

				case reverse: { // To Op: When driver sets robot to REVERSE
					setRough(true);
					time(.5);
					setRough(false);

					break;
				}
				
				case error: { // "You can't do that!"
					setRough(true);
					time(.25);
					setRough(false);
					time(.1);
					setSoft(true);
					time(.25);
					setSoft(false);
					
					break;
				}

				default: {
					DriverStation.reportError("Rumbler: Unknown rumble with name \"" + type.name() + "\".\n", false);
					break;
				}
				}
			}
		}).start();
	}

	private void time(double secs) {
		try {
			Thread.sleep(Math.round(secs * 1000));
		} catch (InterruptedException e) {
			DriverStation.reportError("Rubler: Thread.sleep() call failed. Timing may be off.", false);
			e.printStackTrace();
		}
	}

	public static enum Rumbles {
		// (Disabled) Test Rumbles
		// L1R1, LR1, L2, R2,
		
		// Directional indicators
		reverse, forward,
		
		// "You can't do that!"
		error,
	}
}