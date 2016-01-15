package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	
	TalonSRX intakeFront;
	TalonSRX intakeBack;
	TalonSRX shooterHinge;
	DigitalInput limitSwitch = new DigitalInput(RobotMap.limitSwitchPort);

	public ShooterSubsystem() {
		// Constructor for the subsystem sets the different motors,
		// intake1, intake2, and shooterHinge, to the ports 0, 1, and 2.
		intakeFront = new TalonSRX(RobotMap.intakeFrontPort);
		intakeBack = new TalonSRX(RobotMap.intakeBackPort);
		shooterHinge = new TalonSRX(RobotMap.hingePort);
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
	
	public void changeShooterAngle(double speed){
		shooterHinge.set(speed);
	}
	
	public void stop(){
		intakeFront.set(0);
		intakeBack.set(0);
	}
	

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
