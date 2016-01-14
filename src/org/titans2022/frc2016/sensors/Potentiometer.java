package org.titans2022.frc2016.sensors;

import org.titans2022.frc2016.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Potentiometer extends AnalogPotentiometer{

	public Potentiometer(int channel, double fullRange, double offset) {
		super(channel, fullRange, offset);
	
	}

}
