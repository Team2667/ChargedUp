package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class WristIn extends CommandBase{

	private Wrist wrist;
	
	public WristIn(Wrist wrist){
		this.wrist = wrist;
	}

	@Override
	public void initialize(){
		wrist.set(-.2);
	}

	@Override
	public boolean isFinished(){
		return wrist.reversedLimitSwitchPressed();
	}

	@Override
	public void end(boolean interrupted){
		wrist.stop();
	}
	
}
