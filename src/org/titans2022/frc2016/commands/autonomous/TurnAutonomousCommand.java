package org.titans2022.frc2016.commands.autonomous;

import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.subsystems.DriveSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnAutonomousCommand extends Command {
	DriveSubsystem driveSubsystem;
	double angle;
	boolean running = true;
	double rightDistance;
	double leftDistance;


    public TurnAutonomousCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	driveSubsystem = Robot.driveSubsystem;
		this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double robotWidth = ConstantMap.robotWidth;
    	double circumfrence = robotWidth*Math.PI;
    	double distance = (angle/360)*circumfrence;
    	
    	if(angle > 0){
    		rightDistance = -distance;
    		leftDistance = distance;
    	}
    	else{
    		rightDistance = distance;
    		leftDistance = -distance;
    	}
    	
    	driveSubsystem.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveSubsystem.enableRightPIDController(rightDistance);
    	driveSubsystem.enableLeftPIDController(leftDistance);
    	while(driveSubsystem.rightPIDOnTarget() == false && driveSubsystem.leftPIDOnTarget() == false){
			driveSubsystem.setRightSpeed(driveSubsystem.getRightPIDOutput());
			driveSubsystem.setLeftSpeed(driveSubsystem.getLeftPIDOuput());
			
			SmartDashboard.putNumber("Right Speed", driveSubsystem.getRightEncoderRate());
			SmartDashboard.putNumber("Right Encoder Distance", driveSubsystem.getRightEncoderDistance());
			SmartDashboard.putNumber("Left Speed", driveSubsystem.getLeftEncoderRate());
			SmartDashboard.putNumber("Left Encoder Distance", driveSubsystem.getLeftEncoderDistance());
		
			System.out.println("Turning");
    	}
    	long time = System.currentTimeMillis();
		driveSubsystem.disablePIDControllers();
	    driveSubsystem.setRightSpeed(0);
	    driveSubsystem.setLeftSpeed(0);
    	while(System.currentTimeMillis() < time + 1000){
    		
    	}
	    driveSubsystem.resetEncoders();
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
