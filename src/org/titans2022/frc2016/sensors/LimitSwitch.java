package org.titans2022.frc2016.sensors;

import edu.wpi.first.wpilibj.DigitalInput;

/**
 * A limit switch.
 * 
 * @author nick
 *
 */

public class LimitSwitch extends DigitalInput {
	/**
	 * Control whether the LimitSwitch is enabled.
	 */
	public boolean isEnabled;

	/**
	 * Create a limit switch given its channel
	 * 
	 * @param channel
	 *            the DIO channel for the digital input 0-9 are on-board, 10-25
	 *            are on the MXP
	 */
	public LimitSwitch(int channel) {
		super(channel);
	}

	/**
	 * @return a boolean indicating whether the switch is tripped.
	 */
	public boolean getState() {
		if (!isEnabled) {
			return true;
		}
		return super.get();
	}
}
