package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.ConstantMap;
import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	// internal drive system
	private RobotDrive drive;
	SensorSubsystem system;
	private double rightSpeed, leftSpeed;
	
	//Encoders
	private Encoder rightEncoder;
	private Encoder leftEncoder;
	
	//PID
	public PIDController rightController;
	public PIDOutputRight pidOutputRight;
	
	public PIDController leftController;
	public PIDOutputLeft pidOutputLeft;

	public DriveSubsystem() {
		drive = new RobotDrive(new CANTalon(RobotMap.frontLeftMotor), new CANTalon(RobotMap.backLeftMotor),
				new CANTalon(RobotMap.frontRightMotor), new CANTalon(RobotMap.backRightMotor));
		drive.setSafetyEnabled(true);
		
		//Initialize Encoders
		rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB, false, EncodingType.k4X);
		leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB, false, EncodingType.k4X);
		//Set Encoder distance per pulse
		rightEncoder.setDistancePerPulse(ConstantMap.driveEncoderDistPerTick);
		leftEncoder.setDistancePerPulse(ConstantMap.driveEncoderDistPerTick);
				
	}

	protected void initDefaultCommand() {
	}

	public double getRightSpeed() {
		return rightSpeed;
	}

	public double getLeftSpeed() {
		return leftSpeed;
	}

	public void setSpeed(double rightSpeed, double leftSpeed) {
		drive.tankDrive(rightSpeed, leftSpeed);
		this.rightSpeed = rightSpeed;
		this.leftSpeed = leftSpeed;
	}
	
	public void setLeftSpeed(double speed){
		drive.tankDrive(speed, rightSpeed);
	}
	
	public void setRightSpeed(double speed){
		drive.tankDrive(leftSpeed, speed);
	}

	public void rotate(double deltaAngle) {
		// TODO implement
	}

	public void stop() {
		drive.stopMotor();
		rightSpeed = leftSpeed = 0;
	}
	
	
	//Get Encoder Distances
	public double getRightEncoderDistance(){
		System.out.println(rightEncoder.getDistance());
		return rightEncoder.getDistance();
	}
	
	public double getLeftEncoderDistance(){
		System.out.println(leftEncoder.getDistance());
		return leftEncoder.getDistance();
	}
	
	//Get Encoder Raw Values
	public int getRightEncoderRawValue(){
		System.out.println(rightEncoder.get());
		return rightEncoder.get();
	}
	
	public int getLeftEncoderRawValue(){
		System.out.println(leftEncoder.get());
		return leftEncoder.get();
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
	
	//This method will use the PID controllers to get data from the encoders
	//and give an output to the motors based on the PID variables
	public void driveDistanceStraight(double inches) {
		resetEncoders();
		
		pidOutputRight = new PIDOutputRight();
		rightController = new PIDController(ConstantMap.pC, ConstantMap.iC, ConstantMap.dC, rightEncoder, pidOutputRight);
	
		pidOutputLeft = new PIDOutputLeft();
		leftController = new PIDController(ConstantMap.pC, ConstantMap.iC, ConstantMap.dC, leftEncoder, pidOutputLeft);
		
		rightController.setOutputRange(-1, 1);
		leftController.setOutputRange(-1, 1); 
		
		rightController.setSetpoint(inches);
		leftController.setSetpoint(inches);
		
		rightController.enable();
		leftController.enable();
		
		while(rightController.isEnable() || leftController.isEnable()){
			setRightSpeed(pidOutputRight.getOutput());
			setLeftSpeed(pidOutputLeft.getOutput());
		}
	
	}
}
