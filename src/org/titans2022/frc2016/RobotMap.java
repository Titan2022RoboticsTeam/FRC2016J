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
	// sensor ports 
	public static int gyroPort = -1;// not a real value
	public static int lidarPort = -1;
	public static int limitSwitchPort = -1;
	public static int potentiometerPort = -1;
	public static final String cameraName = "cam0";
	//sensor configuration
	public static double lidarScalar = 1;
	// Motor ports for intake and hinge in shooter
	public static int intakeFrontPort = 0;
	public static int intakeBackPort = 1;
	public static int hingePort = 2;
	public static int shooterAnglePort = -1;
	// Scalar system motors (temp values)
	public static int tapeMotorPort = -1;
	public static int winchPort = -1;
	//Encoders
	public static int rightEncoderPortA = 0;
	public static int rightEncoderPortB = 1;
	public static int leftEncoderPortA = 2;
	public static int leftEncoderPortB = 3;

	
	
}
