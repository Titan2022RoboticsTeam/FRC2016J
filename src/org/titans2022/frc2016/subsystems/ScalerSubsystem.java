package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Talon;

public class ScalerSubsystem extends Subsystem {
	private Talon tapeMotor;
	private Talon winch;
	private double tapeSpeed, winchSpeed;

	public ScalerSubsystem() {
		tapeMotor = new Talon(RobotMap.tapeMotorPort);
		winch = new Talon(RobotMap.winchPort);
	}

	@Override
	protected void initDefaultCommand() {
	}

	public double getTapeSpeed() {
		return tapeSpeed;
	}

	public double getWinchSpeed() {
		return winchSpeed;
	}

	public void setTapeSpeed(double speed) {
		tapeMotor.set(speed);
		tapeSpeed = speed;
	}

	public void setWinchSpeed(double speed) {
		winch.set(speed);
		winchSpeed = speed;
	}

	public void stop() {
		tapeMotor.stopMotor();
		winch.stopMotor();
		tapeSpeed = winchSpeed = 0;
	}

}
