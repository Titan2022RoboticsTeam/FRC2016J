package org.titans2022.frc2016.subsystems;

import org.titans2022.frc2016.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class VisionSubsystem extends Subsystem {
	// static enum DefenseType{
	//
	// LOW_BAR, PORTCULLIS, CHEVAL_DE_FRISE,
	// MOAT, RAMPARTS, DRAWBRIDGE, SALLY_PORT,
	// ROCK_WALL, ROUGH_TERRAIN
	//
	// }

	USBCamera camera;
	CameraServer server;

	public VisionSubsystem() {
		camera = new USBCamera(RobotMap.cameraName);
		server = CameraServer.getInstance();
		server.setQuality(50);

	}

	@Override
	protected void initDefaultCommand() {

		server.startAutomaticCapture(camera);

	}
}