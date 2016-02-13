package org.titans2022.frc2016.subsystems;

import java.util.HashMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class CameraSubsystem extends Subsystem {

	NetworkTable table;
	//Hashmap with network table values from camera
	private HashMap<String, Double> networkTableValues= new HashMap();
	//Hashnmap of coordinates of rectangle from Roborealm
	private HashMap<String, Double> coordinates= new HashMap();	
	//The width of image in pixels
	private double cameraWidthPixel = 640;
	//The height of image in pixels
	private double cameraHeightPixel = 480;
	//The field of view of camera
	private double cameraFOV = 54;
	//The vertical distance of tower
	private double towerHeight = 45-14;
	
	private double rectangleHeightInches = 12;
	private double rectangleWidthInches = 20;
	public double distanceFromTower = 0;
	
	public static boolean shoot = false;
	
	 /*p1 = top left corner of box
	 * p2 = top right
	 * p3 = bottom left
	 * p4 = bottom right
	 */

	public CameraSubsystem() {
		// TODO Auto-generated constructor stub		
		networkTableValues.put("area", (double) 0);
		networkTableValues.put("centerX", (double) 0);
		networkTableValues.put("centerY", (double) 0);
		networkTableValues.put("width", (double) 0);
		networkTableValues.put("height", (double) 0);
		
		coordinates.put("p1x", (double) 0);
		coordinates.put("p1y", (double) 0);
		coordinates.put("p2x", (double) 0);
		coordinates.put("p2y", (double) 0);
		coordinates.put("p3x", (double) 0);
		coordinates.put("p3y", (double) 0);
		coordinates.put("p4x", (double) 0);
		coordinates.put("p4y", (double) 0);

		
	}
	
    @Override
	protected void setDefaultCommand(Command command) {
		// TODO Auto-generated method stub
		super.setDefaultCommand(command);
//		while(true){
//			for (double areaPixel : table.getNumberArray("areaPixel", new double[0])) {
//	            System.out.println("Got contour with areaPixel=" + areaPixel);
//	        }
//			Timer.delay(0.5);
//		}
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
	public void getNetworkTableValues(){
//		while(table.getNumberArray("myContoursReport/areaPixel", new double[0]).length != 1){
//			
//		}
		table = NetworkTable.getTable("SmartDashboard")
;		
		System.out.println("GETTING VALUES");

		try{
			//Get area***********************************
			double[] coordinatesArray = (double[]) table.getValue("BFR_COORDINATES", new double[8]);
			if(coordinatesArray.length == 8){
				coordinates.replace("p1x", coordinatesArray[0]);
				coordinates.replace("p1y", coordinatesArray[1]);
				coordinates.replace("p2x", coordinatesArray[2]);
				coordinates.replace("p2y", coordinatesArray[3]);
				coordinates.replace("p3x", coordinatesArray[4]);
				coordinates.replace("p3y", coordinatesArray[5]);
				coordinates.replace("p4x", coordinatesArray[6]);
				coordinates.replace("p4y", coordinatesArray[7]);
			}
			
			
			
		}catch(NullPointerException e){
			System.out.println(e);
		}
		
		/*p2 = top left corner of box
		 * p1 = top right
		 * p3 = bottom left
		 * p4 = bottom right
		 * origin is bottom left
		 */
		
		//Get average width of rectangle
		double widthOne = Math.sqrt(Math.pow(coordinates.get("p2x")-coordinates.get("p1x"), 2)+(Math.pow(coordinates.get("p2y")-coordinates.get("p1y"), 2)));
	
		double widthTwo = Math.sqrt(Math.pow(coordinates.get("p3x")-coordinates.get("p4x"), 2)+(Math.pow(coordinates.get("p3y")-coordinates.get("p4y"), 2)));

		double averageWidth = (widthOne + widthTwo)/2;
	
		networkTableValues.replace("width", averageWidth);
		
		//Get average height of rectangle
		
		double heightOne = Math.sqrt(Math.pow(coordinates.get("p1x")-coordinates.get("p4x"), 2)+(Math.pow(coordinates.get("p1y")-coordinates.get("p4y"), 2)));

		double heightTwo = Math.sqrt(Math.pow(coordinates.get("p2x")-coordinates.get("p3x"), 2)+(Math.pow(coordinates.get("p2y")-coordinates.get("p3y"), 2)));

		double averageHeight = (heightOne + heightTwo)/2;
		
		networkTableValues.replace("height", averageHeight);
		
		
		
		
	}
	
	public double getDistance(){
		
		getNetworkTableValues();
		
		//Get the percent of the frame the rectangle takes up in pixels vertically
//		
		double percentHeight = networkTableValues.get("height")/cameraHeightPixel;
		
		//Get the height of the frame in inches
		
		double cameraHeightInches = rectangleHeightInches/percentHeight;
		
		//Get the width of the frame in inches
		
		double cameraWidthInches = cameraHeightInches*(cameraWidthPixel/cameraHeightPixel);
		
		//Get the distanceFromTower from the camera to the goal
		
		distanceFromTower = (cameraWidthInches/2)/Math.tan(Math.toRadians((cameraFOV)/2));
		
		//Get the distanceFromTower from the camera to the base of the tower
		
		double horizontalDistance = Math.sqrt(distanceFromTower*distanceFromTower - towerHeight*towerHeight);
		
		return horizontalDistance;
	}
	
	public double getOffset(){
		
		//Get the percent of the frame the rectangle takes up in pixels vertically
		double percentHeight = networkTableValues.get("height")/cameraHeightPixel;
		//Get the height of the frame in inches
		double cameraHeightInches = networkTableValues.get("height")/percentHeight;
		//Get the width of the frame in inches
		double cameraWidthInches = cameraHeightInches*(cameraWidthPixel/cameraHeightPixel);
		//Get the distanceFromTower from the camera to the goal
		distanceFromTower = (cameraWidthInches/2)/Math.tan(cameraFOV*(Math.PI/180));
		//Get the distanceFromTower from the camera to the base of the tower
		double horizontalDistance = Math.sqrt(Math.pow(distanceFromTower, 2) - Math.pow(towerHeight, 2));
		//Get offset in inches
		double distanceFromCenterInches = (networkTableValues.get("centerX") / cameraWidthPixel)*cameraWidthInches;
		double offset = Math.atan((distanceFromCenterInches/distanceFromTower)*(180/Math.PI));
		
		System.out.println("OFFSET: " + offset);
		return offset;
	}
	
	
	
}