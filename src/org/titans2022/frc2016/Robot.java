
package org.titans2022.frc2016;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.titans2022.frc2016.commands.AutoFireCommand;
import org.titans2022.frc2016.commands.AutonomousCommand;
import org.titans2022.frc2016.commands.DriveCommand;
import org.titans2022.frc2016.controllers.Attack3;
import org.titans2022.frc2016.controllers.Xbox;
import org.titans2022.frc2016.subsystems.DriveSystem;
import org.titans2022.frc2016.subsystems.SensorSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static Robot robot;
	// Controllers
	public Xbox xbox;
	public Attack3 attack3, attack4;
	// Robot Drive subsystem
	public DriveSystem driveSystem;
	public SensorSystem sensorSystem;
	// Robot internal state
	/// none yet
	// Robot Commands
	AutonomousCommand autonomousCommand;
	AutoFireCommand fireCommand;
	DriveCommand driveCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		robot = this;
		// initialize the controllers
		xbox = new Xbox(RobotMap.xboxPort);
		attack3 = new Attack3(RobotMap.attack3Port);
		attack4 = new Attack3(RobotMap.attack4Port);
		// initialize drive subsystem
		driveSystem = new DriveSystem();
		// instantiate the command(s) used for the autonomous period
		autonomousCommand = new AutonomousCommand();//??? Add more commands???
		// instantiate the command(s) used for the teleop period
		fireCommand = new AutoFireCommand();
		driveCommand = new DriveCommand(driveSystem);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		autonomousCommand.cancel();
		// start the teleop commands
		fireCommand.start();
		driveCommand.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		// disable robot
		driveSystem.stop();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}