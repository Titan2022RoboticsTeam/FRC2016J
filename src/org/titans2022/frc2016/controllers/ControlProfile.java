package org.titans2022.frc2016.controllers;

import org.titans2022.frc2016.Robot;

public class ControlProfile {

	String name;

	public GenericController driveController;
	public GenericController shooterController;

	public int invertButton; // button that, when pressed, inverts the drive
								// controls
	public int moveStick; // controls movement power (controls forward/backward
							// motion)
	public int moveStick2; // controls the left/right distribution of movement
							// power (controls turning)
	public boolean dualStickMove; // makes moveStick control left wheels and
									// moveStick2 control right wheels.
	public int scalerButton; // button that, while pressed, extends the scaler.
								// While pressed again, it retracts it.

	public int shooterFireButton; // button that controls shooter.
	public boolean autoshooter; /*
								 * if true, pressing shooter button freezes
								 * drive controls and automatically aims and
								 * shoots for the goal; long pressing allows
								 * manual firing. if false, pressing shooter
								 * button simply fires the shooter (it does not
								 * spin up the wheels or aim).
								 */
	public int shooterSpinUpButton;
	public int shooterIntakeButton;
	/*
	 * public int shooterSpinUpStick; //controls the speed of the shooter wheels
	 * public int spinUpStickInvertButton; /*button that inverts the direction
	 * of the spinUpButton. -1 makes it switch to pull in when the shooter is
	 * pointed down and push out when the shooter is pointed up
	 */
	public int shooterManualAimStick;

	public ControlProfile() {
		// initialize the default shooter profile
		name = "Test";
		driveController = Robot.robot.xbox;
		shooterController = Robot.robot.xbox;

		invertButton = Xbox.A_BUTTON;
		moveStick = Xbox.LEFT_YAXIS;
		moveStick2 = Xbox.LEFT_XAXIS;
		dualStickMove = false;
		scalerButton = Xbox.Y_BUTTON;
		shooterFireButton = Xbox.X_BUTTON;
		autoshooter = false;
		shooterSpinUpButton = Xbox.RIGHT_BUMPER;
		shooterIntakeButton = Xbox.LEFT_BUMPER;
		shooterManualAimStick = Xbox.RIGHT_YAXIS;
	}

	public ControlProfile(int profileNumber) {
		switch (profileNumber) {
		default:
			name = "Test";
			driveController = Robot.robot.xbox;
			shooterController = Robot.robot.xbox;

			invertButton = Xbox.A_BUTTON;
			moveStick = Xbox.LEFT_YAXIS;
			moveStick2 = Xbox.LEFT_XAXIS;
			dualStickMove = false;
			scalerButton = Xbox.Y_BUTTON;
			shooterFireButton = Xbox.X_BUTTON;
			autoshooter = false;
			shooterSpinUpButton = Xbox.RIGHT_BUMPER;
			shooterIntakeButton = Xbox.LEFT_BUMPER;
			shooterManualAimStick = Xbox.RIGHT_YAXIS;
			break;
		case 0:
			name = "Unfinished Standard";
			driveController = Robot.robot.xbox;
			shooterController = Robot.robot.attack3;

			invertButton = Xbox.A_BUTTON;
			moveStick = Xbox.LEFT_YAXIS;
			moveStick2 = Xbox.LEFT_XAXIS;
			dualStickMove = false;
			scalerButton = Xbox.Y_BUTTON;

			shooterFireButton = 1;
			autoshooter = false;
			shooterSpinUpButton = 5;
			shooterIntakeButton = 6;
			shooterManualAimStick = Xbox.RIGHT_YAXIS;
			break;
		}
	}

	public String getName() {
		return name;
	}

}
