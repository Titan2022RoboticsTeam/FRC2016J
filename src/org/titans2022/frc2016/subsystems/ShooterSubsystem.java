package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.sensors.AngularPotentiometer;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	TalonSRX intakeFront;
	TalonSRX intakeBack;
	AngularPotentiometer shooterAngle;
	TalonSRX shooterHinge;
	DigitalInput limitSwitch;
	

	final double HINGE_SPEED = 0.5;

	public ShooterSubsystem() {
		// Constructor for the subsystem sets the different motors,
		// intake1, intake2, and shooterHinge, to the ports 0, 1, and 2.
		intakeFront = new TalonSRX(RobotMap.intakeFrontPort);
		intakeBack = new TalonSRX(RobotMap.intakeBackPort);
		shooterHinge = new TalonSRX(RobotMap.hingePort);
		shooterAngle = new AngularPotentiometer(RobotMap.shooterAnglePort, 1, 0);
		limitSwitch = new DigitalInput(RobotMap.limitSwitchPort);
		
	}
	
	public void setIntake (int speed) {
		if(speed == 1){
			intakeFront.set(1);
			intakeBack.set(1);
		}
		else if(speed == -1){
			if(limitSwitch.get() == false){
				intakeFront.set(-1);
				intakeBack.set(0);
			}
			else{
				intakeFront.set(0);
				intakeBack.set(0);
			}
		}
		else if(speed == 0){
			intakeFront.set(0);
			intakeBack.set(0);
		}
		
	}
	
	public void changeShooterAngle(double angle){
		boolean higher = angle-shooterAngle.getAngleDegrees()>0;
		while(Math.abs(angle-shooterAngle.getAngleDegrees())>1){
			shooterHinge.set(HINGE_SPEED*(higher?1:-1));
		}
	}
	
	public void stop(){
		intakeFront.set(0);
		intakeBack.set(0);
	}
	

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
