package org.titans2022.frc2016.sensors;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class AngularPotentiometer extends AnalogPotentiometer{

	public AngularPotentiometer(int channel, double fullRange, double offset) {
		super(channel, fullRange, offset);
	}

	public double getAngleDegrees(){
		return get() * 360;
	}
	
	public double getAngleRadians(){
		return get() * Math.PI * 2;
	}
}
