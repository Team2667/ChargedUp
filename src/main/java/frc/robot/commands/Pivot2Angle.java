package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Pivot;
public class Pivot2Angle extends CommandBase {
    Pivot pivot;
    Constants.GoalPos goalPos;
    private double numRotations;

    public Pivot2Angle(Pivot pivot, Constants.GoalPos goalPos) {
        addRequirements(pivot);
        this.pivot=pivot;
        this.goalPos = goalPos;
    }

    @Override
    public void initialize() {
        numRotations = pivot.getRotationsToGoalPosition(goalPos);
        pivot.setPosition(numRotations);
    }
    
    @Override
    public boolean isFinished() {
        return pivot.isAtSetPoint(numRotations);
    }
}
