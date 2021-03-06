package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.controllers.UniversalController;
import org.titans2022.frc2016.subsystems.ShooterSubsystem;
import org.titans2022.frc2016.subsystems.Vector3;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command {

	ShooterSubsystem shooterSubsystem;
	double attackThrottleSensitivity = 0.1; // is this ever used?
	AnalogPotentiometer potentiometer;

	boolean shooterInverted;
	
	public ShooterCommand() {
		shooterSubsystem = Robot.shooterSubsystem;
		potentiometer = new AnalogPotentiometer(RobotMap.potentiometerPort, 90, 0);
	}

	@Override
	protected void initialize() {
		requires(Robot.shooterSubsystem);
	}

	@Override
	protected void end() {
		shooterSubsystem.stop();

	}

	@Override
	protected void execute() {

		double shooterAim = UniversalController.shooterSpeedAxis;

		if (UniversalController.shooterIntakeButtonPressed == true) {
			shooterSubsystem.setIntake(-1);
		} else if (UniversalController.shooterSpinUpButtonPressed == true) {
			shooterSubsystem.setIntake(1);
		} else {
			shooterSubsystem.setIntake(0);
		}

		// TODO add in actual automatic fire code

		if (shooterAim > attackThrottleSensitivity && potentiometer.get() < 90) {
			shooterSubsystem.manualChangeShooterAngle(shooterAim);
		} else if (shooterAim < -attackThrottleSensitivity && potentiometer.get() > 0) {
			shooterSubsystem.manualChangeShooterAngle(shooterAim);
		}

	}

	public void addFireTarget(Vector3 target) {
		// TODO implement
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
