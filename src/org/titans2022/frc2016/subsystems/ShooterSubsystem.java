package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	
	TalonSRX intakeFront;
	TalonSRX intakeBack;
	TalonSRX shooterHinge;

	public ShooterSubsystem() {
		// Constructor for the subsystem sets the different motors,
		// intake1, intake2, and shooterHinge, to the ports 0, 1, and 2.
		intakeFront = new TalonSRX(RobotMap.intakeFrontPort);
		intakeBack = new TalonSRX(RobotMap.intakeBackPort);
		shooterHinge = new TalonSRX(RobotMap.hingePort);
	}

	public ShooterSubsystem(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}
	
	public void setIntakeFront (double speed) {
		intakeFront.set(speed);
	}
	
	public void setIntakeBack (double speed) {
		intakeBack.set(speed);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
