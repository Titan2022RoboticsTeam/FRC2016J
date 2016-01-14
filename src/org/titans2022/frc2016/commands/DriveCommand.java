package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.controller.Xbox;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command controls the Robot's Tank Drive.
 */
public class DriveCommand extends Command {
	boolean inverted;
	public DriveCommand() {
	}

	protected void initialize() {
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

		Robot.robot.drive.tankDrive(leftValue, rightValue);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.robot.drive.stopMotor();
	}

	protected void interrupted() {
		end();
	}

}
