
package org.titans2022.frc2016;

import org.titans2022.frc2016.commands.DriveCommand;
import org.titans2022.frc2016.commands.ScalerCommand;
import org.titans2022.frc2016.commands.ShooterCommand;
import org.titans2022.frc2016.subsystems.CameraSubsystem;
import org.titans2022.frc2016.subsystems.DriveSubsystem;
import org.titans2022.frc2016.subsystems.ScalerSubsystem;
import org.titans2022.frc2016.subsystems.SensorSubsystem;
import org.titans2022.frc2016.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// Robot Drive subsystem
	public static DriveSubsystem driveSubsystem = new DriveSubsystem();
	public static SensorSubsystem sensorSubsystem = new SensorSubsystem();
	public static ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static ScalerSubsystem scalerSubsystem = new ScalerSubsystem();
	public static CameraSubsystem cameraSubsystem = new CameraSubsystem();
	// Robot internal state
	/// none yet
	// Robot Commands
	Command autonomousCommand;
	DriveCommand driveCommand;
	ShooterCommand shooterCommand;
	ScalerCommand scalerCommand;
	// For Choosing Autonomous Strategy
	SendableChooser autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		// instantiate the command(s) used for the teleop period
		shooterCommand = new ShooterCommand();
		driveCommand = new DriveCommand();
		scalerCommand = new ScalerCommand();
		// instantiate SendableChooser
		autoChooser = new SendableChooser();
		// AutoChooser:
		// autoChooser.addObject("Name of Strategy", new
		// AutonomousCommandStrategy());
		SmartDashboard.putData("Autonomous Mode Chooser", autoChooser);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = (Command) autoChooser.getSelected();
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
		scalerCommand.start();
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