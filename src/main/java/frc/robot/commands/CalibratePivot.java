package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;
public class CalibratePivot extends CommandBase {
	private Pivot pivot;
	public CalibratePivot(Pivot pivot) {
		addRequirements(pivot);
		this.pivot=pivot;

    }

	@Override
	public void initialize() {
		pivot.set(-0.1);	
	}

	@Override
	public boolean isFinished() {
		return pivot.getLitch();
	}

	@Override
	public void end(boolean interrupted) {
		pivot.stop();
	}
}
