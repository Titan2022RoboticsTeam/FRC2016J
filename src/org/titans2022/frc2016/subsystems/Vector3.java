package org.titans2022.frc2016.subsystems;

/**
 * A 3d vector
 * 
 * @author nick
 */
public class Vector3 {
	public double x, y, z;

	public Vector3() {
		this(0, 0, 0);
	}

	public Vector3(double s) {
		this(s, s, s);
	}

	/**
	 * Creates a new vector
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public Vector3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * @param z
	 */
	public Vector3(Vector3 source) {
		this(source.x, source.y, source.z);
	}

	public Vector3 clone() {
		return new Vector3(x, y, z);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}

	public void normalize() {
		double mag = magnitude();
		this.x /= mag;
		this.y /= mag;
		this.z /= mag;
	}

	public double dot(Vector3 v) {
		return x * v.x + y * v.y + z * v.z;
	}

}
