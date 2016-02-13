package org.titans2022.frc2016.commands.autonomous;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
*/
public class DriveStraightAutonomousCommand extends Command {
	DriveSubsystem driveSubsystem;
	boolean running = true;
	public double distance;

   public DriveStraightAutonomousCommand(double distance) {
       // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
	   	requires(Robot.driveSubsystem);
	   	driveSubsystem = Robot.driveSubsystem;
	   	this.distance = distance;
   }

   // Called just before this Command runs the first time
   protected void initialize() {
   		driveSubsystem.resetEncoders();
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
	   	driveSubsystem.enableLeftPIDController(distance);
	   	driveSubsystem.enableRightPIDController(distance);
	   	while(running){
			driveSubsystem.setRightSpeed(driveSubsystem.getRightPIDOutput());
						
			driveSubsystem.setLeftSpeed(driveSubsystem.pidOutputLeft.getOutput());
   	
	    	if(driveSubsystem.getRightEncoderDistance() > distance - 0.5 && driveSubsystem.getRightEncoderDistance() < distance + 0.5){
				if(driveSubsystem.getLeftEncoderDistance() > distance - 0.5 && driveSubsystem.getLeftEncoderDistance() < distance + 0.5){
					running = false;
					System.out.println(running);
				}
			}
	   	}
	   	long time = System.currentTimeMillis();
		driveSubsystem.disablePIDControllers();
	    driveSubsystem.setRightSpeed(0);
	    driveSubsystem.setLeftSpeed(0);
	   	while(System.currentTimeMillis() < time + 1000){
	   		
	   	}
	    driveSubsystem.resetEncoders();
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished() {
       return !running;
   }

   // Called once after isFinished returns true
   protected void end() {
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted() {
   }
}
