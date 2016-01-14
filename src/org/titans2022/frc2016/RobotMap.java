package org.titans2022.frc2016;

import org.titans2022.frc2016.controller.Xbox;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// drivetrain subsystem.
	public static int frontLeftMotor = 1;
	public static int frontRightMotor = 2;
	public static int backLeftMotor = 3;
	public static int backRightMotor = 4;

	// controller port
	public static int xboxPort = 0;
	public static int attack3Port = 1;
	public static int attack4Port = 2;
	
	//controller configuration
	public static int invertButton = Xbox.A_BUTTON;
	public static double sensitivity = 0.05;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
