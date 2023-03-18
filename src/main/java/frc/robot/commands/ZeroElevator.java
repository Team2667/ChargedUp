package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class ZeroElevator extends CommandBase {
	private Elevator elevator;
	public ZeroElevator(Elevator elevator)
	{
		this.elevator=elevator;	
		addRequirements(elevator);
	}

	@Override
	public void initialize()
	{
		elevator.set(-.5);
	}

	@Override
	public boolean isFinished()
	{
		System.out.println(elevator.reverseLimitSwitchPressed() + "  !!!!!!!!!!!!!!!!!!!!!!");
		return elevator.reverseLimitSwitchPressed();
	}

	@Override
	public void end(boolean interrupted)
	{
		elevator.stop();
		elevator.zeroElevator();
	}
}
