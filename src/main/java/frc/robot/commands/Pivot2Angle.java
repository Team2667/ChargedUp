package frc.robot.commands;

import java.lang.reflect.Executable;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;
public class Pivot2Angle extends CommandBase {
    Pivot pivot;
    double rotations;

    public Pivot2Angle(Pivot pivot, double rotations) {
        addRequirements(pivot);
        this.pivot=pivot;
        this.rotations=rotations;
    }

    @Override
    public void execute() {
        pivot.setPosition(rotations);
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
