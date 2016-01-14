package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.sensors.Potentiometer;
import org.titans2022.frc2016.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCommand extends Command{
	
	ShooterSubsystem shooterSubsystem;
	double attackThrottleSensitivity = 0.1;
	Potentiometer potentiometer;

	
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
		
		double shooterSpeed = Robot.robot.attack3.getY();

		if(Robot.robot.attack3.getButton(5) == true){
			shooterSubsystem.setIntake(-1);
		}
		else if(Robot.robot.attack3.getButton(6) == true){
			shooterSubsystem.setIntake(1);
		}
		else{
			shooterSubsystem.setIntake(0);
		}
		
		if(shooterSpeed > attackThrottleSensitivity && potentiometer.get() < 90){
			shooterSubsystem.changeShooterAngle(shooterSpeed);
		}
		else if(shooterSpeed < -attackThrottleSensitivity && potentiometer.get() > 0){
			shooterSubsystem.changeShooterAngle(shooterSpeed);
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
