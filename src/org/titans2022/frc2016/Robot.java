
package org.titans2022.frc2016;

import org.titans2022.frc2016.commands.AutonomousCommand;
import org.titans2022.frc2016.commands.DriveCommand;
import org.titans2022.frc2016.commands.ShooterCommand;
import org.titans2022.frc2016.controllers.Attack3;
import org.titans2022.frc2016.controllers.Xbox;
import org.titans2022.frc2016.subsystems.DriveSubsystem;
import org.titans2022.frc2016.subsystems.SensorSubsystem;
import org.titans2022.frc2016.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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
	public DriveSubsystem driveSubsystem;
	public SensorSubsystem sensorSubsystem;
	public static ShooterSubsystem shooterSubsystem;
	// Robot internal state
	/// none yet
	// Robot Commands
	AutonomousCommand autonomousCommand;
	DriveCommand driveCommand;
	ShooterCommand shooterCommand;

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
		driveSubsystem = new DriveSubsystem();
		//initialize shooter subsystem
		shooterSubsystem = new ShooterSubsystem();
		// instantiate the command(s) used for the autonomous period
		autonomousCommand = new AutonomousCommand();//??? Add more commands???
		// instantiate the command(s) used for the teleop period
		shooterCommand = new ShooterCommand();
		driveCommand = new DriveCommand(driveSubsystem);
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
		shooterCommand.start();
		driveCommand.start();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		// disable robot
		driveSubsystem.stop();
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