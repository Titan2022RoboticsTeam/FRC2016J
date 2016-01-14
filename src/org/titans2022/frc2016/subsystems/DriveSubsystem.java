package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem{
	//internal drive system
	private RobotDrive drive;
	SensorSubsystem system;
	private double rightSpeed, leftSpeed;
	public DriveSubsystem() {
		drive = new RobotDrive(RobotMap.frontLeftMotor, RobotMap.backLeftMotor, RobotMap.frontRightMotor,
				RobotMap.backRightMotor);
		drive.setSafetyEnabled(true);
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
	
	
	public void rotate(double deltaAngle){
		// TODO implement
	}
	
	public void stop(){
		drive.stopMotor();
		rightSpeed = leftSpeed = 0;
	}
}
