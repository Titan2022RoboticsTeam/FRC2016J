package org.titans2022.frc2016.controllers;

import edu.wpi.first.wpilibj.command.Command;
import java.util.ArrayList;

//import org.titans2022.frc2016.Robot;
//import org.titans2022.frc2016.controllers.Xbox;

public class UniversalController extends Command {

	private GenericController drive;
	private GenericController shoot;

	private static boolean invertButtonPressed;
	// private static boolean scalerButtonPressed;
	public static boolean shooterSpinUpButtonPressed;
	public static boolean shooterIntakeButtonPressed;

	private static double leftMotorAxis;
	private static double rightMotorAxis;
	public static double shooterAimAxis;
	public static double shooterSpeedAxis;

	private String profileName;
	public ArrayList<ControlProfile> profileList;
	private ControlProfile profile;

	@Override
	protected void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		invertButtonPressed = drive.getButtonValue(profile.invertButton);
		if (profile.dualStickMove) {
			leftMotorAxis = drive.getAxisValue(profile.moveStick);
			rightMotorAxis = drive.getAxisValue(profile.moveStick2);
		} else {
			leftMotorAxis = drive.getAxisValue(profile.moveStick) + drive.getAxisValue(profile.moveStick2);
			rightMotorAxis = drive.getAxisValue(profile.moveStick) - drive.getAxisValue(profile.moveStick2);
		}

		shooterAimAxis = shoot.getAxisValue(profile.shooterManualAimStick);

		shooterSpinUpButtonPressed = shoot.getButtonValue(profile.shooterSpinUpButton);
		shooterIntakeButtonPressed = shoot.getButtonValue(profile.shooterIntakeButton);

	}

	@Override
	protected void initialize() {
		// TODO Auto-generated method stub

		profileList.add(new ControlProfile());
		// get profile name from Smart Dashboard or something
		setProfile(profileName);

	}

	@Override
	protected void interrupted() {
		// This method is intentionally empty
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	private void setProfile(String name) {
		for (int i = 0; i < profileList.size(); i++) {
			if (profileList.get(i).getName() == profileName) {
				profile = profileList.get(i);
				break;
			}
		}
		if (profile == null) {
			profile = new ControlProfile();
		}

		drive = profile.driveController;
		shoot = profile.shooterController;
	}

	// Getters for all of the values
	public static double getLeftMotorSpeed() {
		return leftMotorAxis;
	}

	public static double getRightMotorSpeed() {
		return rightMotorAxis;
	}

	public static boolean getInvertButtonPressed() {
		return invertButtonPressed;
	}

}
