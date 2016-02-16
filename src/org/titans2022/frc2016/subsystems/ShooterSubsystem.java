package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {
	TalonSRX intakeFront;
	TalonSRX intakeBack;
	TalonSRX shooterHinge;
	final double HINGE_SPEED = 0.5;

	DigitalInput ballLimitSwitch = new DigitalInput(RobotMap.ballLimitSwitchPort);
	DigitalInput shooterLimitSwitch = new DigitalInput(RobotMap.shooterLimitSwitchPort);
	AnalogPotentiometer potentiometer = new AnalogPotentiometer(RobotMap.potentiometerPort);

	double currentAngle;
	double zeroAngleOffset;

	static final double DOWN_POSITION_ANGLE = -10.5;
	static final double FLAT_POSITION_ANGLE = 0;
	static final double UP_POSITION_ANGLE = 74.25;

	static final double LIMIT_SWITCH_ANGLE = 0; // This is the angle that the
												// shooter is at when the
												// shooter limit switch is
												// pressed.

	public ShooterSubsystem() {
		// Constructor for the subsystem sets the different motors,
		// intake1, intake2, and shooterHinge, to the ports 0, 1, and 2.
		intakeFront = new TalonSRX(RobotMap.intakeFrontPort);
		intakeBack = new TalonSRX(RobotMap.intakeBackPort);
		shooterHinge = new TalonSRX(RobotMap.hingePort);
	}

	public void setIntake(int speed) {

		if (speed == 1) {
			intakeFront.set(1);
			intakeBack.set(1);
		} else if (speed == -1) {
			if (ballLimitSwitch.get() == false) {
				intakeFront.set(-1);
				intakeBack.set(0);
			} else {
				intakeFront.set(0);
				intakeBack.set(0);
			}
		} else if (speed == 0) {
			intakeFront.set(0);
			intakeBack.set(0);
		}

	}

	public void changeShooterAngle(double angle) {
		boolean higher = angle - potentiometer.get() * 360 > 0;
		while (Math.abs(angle - potentiometer.get() * 360) > 1) {
			shooterHinge.set(HINGE_SPEED * (higher ? 1 : -1));
		}
	}

	public void manualChangeShooterAngle(double speed) {
		shooterHinge.set(speed);
	}

	public void changeShooterAngle(int direction) {

		if (shooterLimitSwitch.get()) {
			zeroAngleOffset = potentiometer.get() - LIMIT_SWITCH_ANGLE;
			currentAngle = LIMIT_SWITCH_ANGLE;
		} else {
			currentAngle = potentiometer.get() + zeroAngleOffset;
		}

		if (direction < 0) {
			// lower shooter to the ground
			while (currentAngle > DOWN_POSITION_ANGLE) {
				shooterHinge.set(-1);
			}
			shooterHinge.set(0);
		} else if (direction == 0) {
			// put shooter in straight position
			while (currentAngle > FLAT_POSITION_ANGLE) {
				shooterHinge.set(-1);
			}
			while (currentAngle < FLAT_POSITION_ANGLE) {
				shooterHinge.set(1);
			}
			shooterHinge.set(0);

		} else if (direction > 0) {
			// put shooter in shooting position
			while (currentAngle < UP_POSITION_ANGLE) {
				shooterHinge.set(1);
			}
			shooterHinge.set(0);
		}
	}

	public void stop() {
		intakeFront.set(0);
		intakeBack.set(0);
		shooterHinge.set(0);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
