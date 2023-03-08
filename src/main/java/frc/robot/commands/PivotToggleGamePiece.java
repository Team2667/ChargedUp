package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;

public class PivotToggleGamePiece extends CommandBase{
    private Pivot pivot;

    public PivotToggleGamePiece(Pivot pivot) {
        this.pivot = pivot;
    }

    @Override
    public void initialize(){
        this.pivot.toggleGamePiece();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
