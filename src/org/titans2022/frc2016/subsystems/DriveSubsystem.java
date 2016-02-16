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

	// Encoders
	private Encoder rightEncoder;
	private Encoder leftEncoder;

	// PID
	public PIDController rightController;
	public PIDOutputRight pidOutputRight;

	public PIDController leftController;
	public PIDOutputLeft pidOutputLeft;

	public DriveSubsystem() {
		drive = new RobotDrive(new CANTalon(RobotMap.frontLeftMotor), new CANTalon(RobotMap.backLeftMotor),
				new CANTalon(RobotMap.frontRightMotor), new CANTalon(RobotMap.backRightMotor));
		drive.setSafetyEnabled(true);

		// Initialize Encoders
		rightEncoder = new Encoder(RobotMap.rightEncoderPortA, RobotMap.rightEncoderPortB, false, EncodingType.k4X);
		leftEncoder = new Encoder(RobotMap.leftEncoderPortA, RobotMap.leftEncoderPortB, false, EncodingType.k4X);
		// Set Encoder distance per pulse
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

	public void setLeftSpeed(double speed) {
		drive.tankDrive(speed, rightSpeed);
	}

	public void setRightSpeed(double speed) {
		drive.tankDrive(leftSpeed, speed);
	}

	public void rotate(double deltaAngle) {
		// TODO implement
	}

	public void stop() {
		drive.stopMotor();
		rightSpeed = leftSpeed = 0;
	}

	// Get Encoder Distances
	public double getRightEncoderDistance() {
		System.out.println(rightEncoder.getDistance());
		return rightEncoder.getDistance();
	}

	public double getLeftEncoderDistance() {
		System.out.println(leftEncoder.getDistance());
		return leftEncoder.getDistance();
	}

	// Get Encoder Raw Values
	public int getRightEncoderRawValue() {
		System.out.println(rightEncoder.get());
		return rightEncoder.get();
	}

	public int getLeftEncoderRawValue() {
		System.out.println(leftEncoder.get());
		return leftEncoder.get();
	}

	// Get Encoder Rates
	public double getRightEncoderRate() {
		return rightEncoder.getRate();
	}

	public double getLeftEncoderRate() {
		return leftEncoder.getRate();
	}

	public void resetEncoders() {
		rightEncoder.reset();
		leftEncoder.reset();
	}

	//PID Methods
		public void enableRightPIDController(double distance){
			rightController.setSetpoint(distance);
			rightController.enable();
		}
		public void disablePIDControllers(){
			rightController.disable();
			leftController.disable();
		}
		
		public void enableLeftPIDController(double distance){
			leftController.setSetpoint(distance);
			leftController.enable();
		}
		
		public double getRightPIDOutput(){
			return pidOutputRight.getOutput();
		}
		
		public double getLeftPIDOuput(){
			return pidOutputLeft.getOutput();
		}

		//Is robot 0.5 inches from target
		public boolean rightPIDOnTarget(){
			return rightController.onTarget();
		}
		
		public boolean leftPIDOnTarget(){
			return leftController.onTarget();
		}
}
