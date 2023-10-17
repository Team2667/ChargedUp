package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.GoalPosition;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import static frc.robot.Constants.GamePieceType;

public class SetMode extends CommandBase{
    private Elevator elevator;
    private Intake intake;
    private GamePieceType type;

    public SetMode(Elevator elevator, Intake intake,GamePieceType type){
        this.elevator = elevator;
        this.intake = intake;
        this.type=type;
        //addRequirements(elevator, intake);
    }
    
    @Override()
    public void initialize(){
        elevator.setGamePieceType(type);
        intake.setGamePieceType(type);
    }
    @Override
    public void execute()
    {
        return;
    }

    @Override()
    public boolean isFinished(){
        return true;
    }
}
