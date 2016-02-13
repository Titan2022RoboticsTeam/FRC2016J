package org.titans2022.frc2016.commands.autonomous;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.subsystems.CameraSubsystem;
import org.titans2022.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
*
*/
public class DriveToShootingRange extends Command {
	
	DriveSubsystem tankDriveSubsystem;
	CameraSubsystem cameraSubsystem;
	boolean running = true;
	public double distanceFromTower;
	public double distanceToMove;
	public double targetDistanceFromTower = 55.61;
	public double offset;
	
	
   public DriveToShootingRange() {
       // Use requires() here to declare subsystem dependencies
       // eg. requires(chassis);
	   	requires(Robot.driveSubsystem);
	   	requires(Robot.cameraSubsystem);
	   	tankDriveSubsystem = Robot.driveSubsystem;
	   	cameraSubsystem = Robot.cameraSubsystem;
   }

   // Called just before this Command runs the first time
   protected void initialize() {
	   	tankDriveSubsystem.resetEncoders();
	   	distanceFromTower = cameraSubsystem.getDistance();
	   	distanceToMove = distanceFromTower - targetDistanceFromTower;
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
	   	tankDriveSubsystem.enableLeftPIDController(distanceToMove);
	   	tankDriveSubsystem.enableRightPIDController(distanceToMove);
	   	System.out.println("Distance To Move: " + distanceToMove);
	   	while(tankDriveSubsystem.rightPIDOnTarget() == false && tankDriveSubsystem.leftPIDOnTarget() == false){
				tankDriveSubsystem.setRightSpeed(tankDriveSubsystem.getRightPIDOutput());
				tankDriveSubsystem.setLeftSpeed(tankDriveSubsystem.getLeftPIDOuput());
				
	   	}
	   	
	   	long time = System.currentTimeMillis();
		tankDriveSubsystem.disablePIDControllers();
	    tankDriveSubsystem.setRightSpeed(0);
	    tankDriveSubsystem.setLeftSpeed(0);
	    
	   	while(System.currentTimeMillis() < time + 1000){
	   		
	   	}
	    tankDriveSubsystem.resetEncoders();
		    
		        
	   	running = false;
	   	
   	
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