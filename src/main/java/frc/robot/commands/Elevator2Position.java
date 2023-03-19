package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import static frc.robot.Constants.GoalPosition;

public class Elevator2Position extends CommandBase{

    private Elevator elevator;
    private GoalPosition goalPos;

    public Elevator2Position(Elevator elevator, GoalPosition goalPos) {
        this.elevator = elevator;
        this.goalPos = goalPos;
    }

    @Override
    public void initialize() {
        elevator.setElevatorPosition(goalPos);
    }

    @Override 
    public boolean isFinished() {
        return elevator.isAtSetPoint(goalPos);
    }

    @Override
    public void end(boolean isInterrupted){
        elevator.stop();
    }
}
