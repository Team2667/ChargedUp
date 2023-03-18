package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class WristOut extends CommandBase{

	private Wrist wrist;
	
	public WristOut(Wrist wrist){
		this.wrist = wrist;
		this.addRequirements(wrist);
	}

	@Override
	public void initialize(){
		wrist.set(.2);
	}

	@Override
	public boolean isFinished(){
		return wrist.forwardLimitSwitchPressed();
	}

	@Override
	public void end(boolean interrupted){
		wrist.stop();
	}
	
}
