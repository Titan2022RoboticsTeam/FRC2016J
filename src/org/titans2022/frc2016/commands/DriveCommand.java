package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.controllers.Xbox;
import org.titans2022.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command controls the Robot's Tank Drive.
 */
public class DriveCommand extends Command {
	protected DriveSubsystem driveSubsystem;
	protected boolean inverted;
	
	public DriveCommand(DriveSubsystem drive) {
		this.driveSubsystem = drive;
	}

	protected void initialize() {
		requires(driveSubsystem);
	}

	protected void execute() {
		Xbox xbox = Robot.robot.xbox;
		if (xbox.GetRawButton(RobotMap.invertButton)) {
			inverted = !inverted;
		}
		double leftValue = xbox.GetLeftY();
		double rightValue = xbox.GetRightY();
		if (inverted) {
			leftValue = xbox.GetRightY();
			rightValue = xbox.GetLeftY();
		}

		driveSubsystem.setSpeed(leftValue, rightValue);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		driveSubsystem.stop();
	}

	protected void interrupted() {
		end();
	}

}
