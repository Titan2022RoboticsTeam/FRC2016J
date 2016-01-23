package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem{
	//internal drive system
	private RobotDrive drive;
	SensorSubsystem system;
	private double rightSpeed, leftSpeed;
	
	//Encoders
	protected Encoder rightEncoder;
	protected Encoder leftEncoder;
	//Encoder Constants
	public static final int driveWheelRadius = 5;
	public static final int pulsePerRotation = 4096;
	public static final double gearRatio = 1/1;
	public static final double driveEncoderPulsePerRot = pulsePerRotation * gearRatio;
	public static final double driveEncoderDistPerTick = (Math.PI * driveWheelRadius * 2) / driveEncoderPulsePerRot;
	public static final double driveEncoderMaxPeriod = .1;
	public static final double driveEncoderMinRate = 10;	
	
	public DriveSubsystem() {
		drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.backLeftMotor, RobotMap.frontRightMotor,
				RobotMap.backRightMotor);
		drive.setSafetyEnabled(true);
		
		//Initialize Encoders
		rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB);
		leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB);
		//Set Encoder distance per pulse
		rightEncoder.setDistancePerPulse(driveEncoderDistPerTick);
		leftEncoder.setDistancePerPulse(driveEncoderDistPerTick);
		//The maximum period (in seconds) where the device is still considered moving
		rightEncoder.setMaxPeriod(driveEncoderMaxPeriod);
		leftEncoder.setMaxPeriod(driveEncoderMaxPeriod);
		//Set the minimum rate before the device is considered stopped
		rightEncoder.setMinRate(driveEncoderMinRate);
		leftEncoder.setMinRate(driveEncoderMinRate);

	}
	
	protected void initDefaultCommand() {
	}
	
	public double getRightSpeed() {
		return rightSpeed;
	}

	public double getLeftSpeed() {
		return leftSpeed;
	}
	
	public void setSpeed(double rightSpeed, double leftSpeed){
		drive.tankDrive(rightSpeed, leftSpeed);
		this.rightSpeed = rightSpeed;
		this.leftSpeed = leftSpeed;
	}
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
		return leftEncoder.getDistance();
	}
	
	//Get Encoder Rates
	public double getRightEncoderRate(){
		return rightEncoder.getRate();
	}
	
	public double getLeftEncoderRate(){
		return leftEncoder.getRate();
	}
	
	public void resetEncoders(){
		rightEncoder.reset();
		leftEncoder.reset();
	}
	
	public void stop(){
		drive.stopMotor();
		rightSpeed = leftSpeed = 0;
	}
}
