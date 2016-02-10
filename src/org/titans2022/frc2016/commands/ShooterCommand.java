package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.controllers.UniversalController;
import org.titans2022.frc2016.sensors.Potentiometer;
import org.titans2022.frc2016.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command{
	
	ShooterSubsystem shooterSubsystem;
	double attackThrottleSensitivity = 0.1; //is this ever used?
	Potentiometer potentiometer;
	
	boolean shooterInverted;

	
	@Override
	protected void initialize() {
		requires(Robot.shooterSubsystem);
		shooterSubsystem =  Robot.shooterSubsystem;
		potentiometer = new Potentiometer(RobotMap.potentiometerPort, 90, 0);
	}

	@Override
	protected void end() {
		shooterSubsystem.stop();
		
	}

	@Override
	protected void execute() {
		
		double shooterAim = UniversalController.shooterSpeedAxis;

		if(UniversalController.shooterIntakeButtonPressed == true){
			shooterSubsystem.setIntake(-1);
		}
		else if(UniversalController.shooterSpinUpButtonPressed == true){
			shooterSubsystem.setIntake(1);
		}
		else{
			shooterSubsystem.setIntake(0);
		}
		
		if(shooterAim > attackThrottleSensitivity && potentiometer.get() < 90){
			shooterSubsystem.changeShooterAngle(shooterAim);
		}
		else if(shooterAim < -attackThrottleSensitivity && potentiometer.get() > 0){
			shooterSubsystem.changeShooterAngle(shooterAim);
		}
		
		
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
