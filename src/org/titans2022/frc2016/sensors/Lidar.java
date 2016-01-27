package org.titans2022.frc2016.sensors;

import org.titans2022.frc2016.RobotMap;
import edu.wpi.first.wpilibj.AnalogInput;
/**
 * Lidar - this will probably not be used.
 * @author nick
 */
public class Lidar extends AnalogInput {

	public Lidar(int channel) {
		super(channel);
	}

	public double getDistance() {
		// this assumes a linear relationship between voltage and distance,
		// verify if that is correct
		double voltage = this.getVoltage();
		double distance = voltage * RobotMap.lidarScalar;
		return distance;
	}
}
