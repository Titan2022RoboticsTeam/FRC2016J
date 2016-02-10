package org.titans2022.frc2016;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// drive-train subsystem.
	public static int frontLeftMotor = -1;//these are not real values
	public static int frontRightMotor = 2;
	public static int backLeftMotor = 3;
	public static int backRightMotor = 4;
	// controller port
	public static int xboxPort = 0;
	public static int attack3Port = 1;
	public static int attack4Port = 2;
	// sensor ports 
	public static int gyroPort = -1;// not a real value
	public static int lidarPort = -1;
	public static int limitSwitchPort = -1;
	public static int potentiometerPort = -1;
	//sensor configuration
	public static double lidarScalar = 1;
	//controller configuration
	public static double sensitivity = 0.05;
	// Motor ports for intake and hinge in shooter
	public static int intakeFrontPort = 0;
	public static int intakeBackPort = 1;
	public static int hingePort = 2;
	
}
