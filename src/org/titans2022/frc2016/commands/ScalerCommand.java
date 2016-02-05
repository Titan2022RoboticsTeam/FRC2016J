package org.titans2022.frc2016.commands;

import org.titans2022.frc2016.ControllerMap;
import org.titans2022.frc2016.Robot;
import org.titans2022.frc2016.controllers.Xbox;
import org.titans2022.frc2016.subsystems.ScalerSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ScalerCommand extends Command {
	protected ScalerSubsystem scalerSubsystem;
	
	private boolean tapeExtending = false;
	private long tapeStart = 0;
	private long tapeElapsed = 0;
	private final double TAPE_SPEED = 0.5;
	
	private final double WINCH_SPEED = 0.5;

	public ScalerCommand(ScalerSubsystem scaler){
		this.scalerSubsystem = scaler;
	}

	@Override
	protected void initialize() {
		requires(scalerSubsystem);
	}

	@Override
	protected void execute() {
		Xbox xbox = Robot.robot.xbox;
		tape(xbox);
		if (xbox.GetRawButton(ControllerMap.winchUp)){
			scalerSubsystem.setWinchSpeed(WINCH_SPEED);
		}
		if(xbox.GetRawButton(ControllerMap.winchDown)){
			scalerSubsystem.setWinchSpeed(-WINCH_SPEED);
		}
		
	}
	
	//Handles the extension and automatic retraction of the tape measure.
	protected void tape(Xbox xbox) {
		if (xbox.GetRawButton(ControllerMap.extendTape)) {
			//Detects the instant when the button is pressed, begins a counter for the tape extending.
			if(!tapeExtending){
				tapeElapsed -= System.nanoTime() - tapeStart;
				
				if(tapeElapsed <=0) tapeElapsed = 0;
				tapeStart = System.nanoTime();
			}
			tapeExtending = true;
		} else if(!xbox.GetRawButton(ControllerMap.extendTape)) {
			/**Detects the instant when the button is released,
			 * finding the time elapsed for tape extending
			 * and beginning a counter of its own for retraction.
			 */
			if(tapeExtending){
				tapeElapsed += System.nanoTime()-tapeStart;
				tapeStart = System.nanoTime();
			}
			tapeExtending = false;
		}
		
		if (tapeExtending){
			scalerSubsystem.setTapeSpeed(TAPE_SPEED);
		//Detects if the motor has retracted the tape far enough, stops if true.
		} else if (tapeElapsed > System.nanoTime()-tapeStart){
			scalerSubsystem.setTapeSpeed(-TAPE_SPEED);
		} else{
			tapeElapsed=0;
			scalerSubsystem.setTapeSpeed(0);
		}
	}
	
	@Override
	protected void end() {
		scalerSubsystem.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
