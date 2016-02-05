package org.titans2022.frc2016;

public class ConstantMap {
	
	//Encoder Constants
	public static double driveWheelRadius = 2.5;
	public static int pulsePerRotation = 4096;
	public static double gearRatio = 1/1;
	public static double driveEncoderPulsePerRot = pulsePerRotation * gearRatio;
	public static double driveEncoderDistPerTick = (Math.PI * driveWheelRadius * 2) / driveEncoderPulsePerRot;
	public static double driveEncoderMaxPeriod = .1;
	public static double driveEncoderMinRate = 10;
	
	//PID Controller Constants
	public static double pC = 1;
	public static double iC = 0.05;
	public static double dC = 0.05;
	public static double kC = 1;

}
