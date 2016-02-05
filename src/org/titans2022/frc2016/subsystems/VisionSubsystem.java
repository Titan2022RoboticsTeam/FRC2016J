package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class VisionSubsystem extends Subsystem {
	static enum DefenseType{
		
	}
	USBCamera camera;
	public VisionSubsystem() {
		camera = new USBCamera(RobotMap.cameraName);
		// TODO Auto-generated constructor stub
	}
	//Do not use 
	protected void initDefaultCommand() {}

	public Vector3 findDefense(DefenseType type){
//		camera.
		return new Vector3(Double.NaN);
	}
	
	
	public boolean hasDefense(DefenseType type){
		double x = findDefense(type).x;
		return x != x;//This is how checking for NaN works- serious
	}
}
