package org.usfirst.frc.team102.robot.subsystems;

import org.usfirst.frc.team102.robot.RobotMap;
import org.usfirst.frc.team102.robot.commands.CameraWithXBox;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraMovement extends Subsystem {

	private Servo xAxis, yAxis;

	private double xPos = .5, yPos = 1;

	public CameraMovement() {
		xAxis = new Servo(RobotMap.xAxisServo);
		yAxis = new Servo(RobotMap.yAxisServo);
	}

	public void moveCamWithXBox(Joystick js) {
		double x = js.getRawAxis(RobotMap.xBoxLeftXAxis);
		double y = js.getRawAxis(RobotMap.xBoxLeftYAxis);

		double delta = 0.02;

		if (x > .25 && xPos < .95)
			xPos += delta;
		if (x < -.25 && xPos > .05)
			xPos -= delta;
		if (y > .5 && yPos < .95)
			yPos += delta;
		if (y < -.5 && yPos > .05)
			yPos -= delta;

		// xAxis.set(xPos); // Ordered to be disabled and based on direction.
		// I.E. forward/reverse
		yAxis.set(yPos);
	}

	public void setServo(int id, double val) {
		if (id == 0)
			xAxis.set(val);
		if (id == 1)
			yAxis.set(val);
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new CameraWithXBox());
	}
}