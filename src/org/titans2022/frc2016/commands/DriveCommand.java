package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.ControllerMap;
import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.controllers.Xbox;
import org.titans2022.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command controls the Robot's Tank Drive.
 */
public class DriveCommand extends Command {
	protected DriveSubsystem driveSubsystem;
	protected boolean inverted = false;
	
	public DriveCommand(DriveSubsystem drive) {
		this.driveSubsystem = drive;
	}

	protected void initialize() {
		requires(driveSubsystem);
	}

	protected void execute() {
		Xbox xbox = Robot.robot.xbox;
		if (xbox.GetRawButton(ControllerMap.invertButton)) {
			inverted = !inverted;
		}
		double scale = inverted ? -ControllerMap.scale: ControllerMap.scale;
		double leftValue = scale * xbox.GetRightY();
		double rightValue = scale * xbox.GetLeftY();

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