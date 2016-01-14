package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;
import org.titans2022.frc2016.sensors.Lidar;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SensorSystem extends Subsystem {
	private Gyro gyro;
	private Lidar rangeFinder;
	public SensorSystem() {
		gyro = new Gyro(RobotMap.gyroPort);
		gyro.initGyro();
		rangeFinder = new Lidar(RobotMap.lidarPort);
		rangeFinder.initAccumulator();
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public void calibrateAngle(){
		gyro.reset();
	}
	
	public double getAngle(){
		return gyro.getAngle();
	}
	
	public double getDistance(){
		return rangeFinder.getDistance();
	}
}
