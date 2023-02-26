package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;
public class HoldInPlace extends CommandBase {
	Pivot pivot;
	public HoldInPlace(Pivot pivot) {
		this.pivot=pivot;
		addRequirements(pivot);
		pivot.setDefaultCommand(this);
	}

	public void initialize() {
		var position=pivot.getPos();
		pivot.setPosition(position);
	}
}
