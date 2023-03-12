package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class Elevator2Position extends CommandBase{

    private Elevator elevator;
    private double goalPos;

    public Elevator2Position(Elevator elevator, double goalPos) {
        this.elevator = elevator;
        this.goalPos = goalPos;
    }

    // TODO: Implement the following methods similar to
    //https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/commands/Pivot2Angle.java

    @Override
    public void initialize() {

    }

    @Override 
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean isInterrupted){
        elevator.stop();
    }
}
