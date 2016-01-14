package org.titans2022.frc2016.sensors;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;
public class Camera {
	CameraServer cameraServer;
	public Camera() {
		// TODO Auto-generated constructor stub
		cameraServer = CameraServer.getInstance();
		NIVision.imaqAdd(null, null, null);
	}
	
	public Point recognizeCastle(){
//		Point p = new Point
		return null;
	}
}