package org.titans2022.frc2016.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

public class PIDOutputRight implements PIDOutput{
	
	double output;
	
	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
	
	public double getOutput(){
		return output;
	}
}
