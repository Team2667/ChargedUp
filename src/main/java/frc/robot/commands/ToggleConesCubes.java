package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Intake;
import static frc.robot.Constants.GamePieceType;

public class ToggleConesCubes extends CommandBase{
    private Elevator elevator;
    private Intake intake;

    public ToggleConesCubes(Elevator elevator, Intake intake){
        this.elevator = elevator;
        this.intake = intake;
        addRequirements(elevator, intake);
    }
    
    @Override()
    public void initialize(){
        var currentGamePieceType = elevator.getCurrentGamePieceType();
        var gamePieceType = currentGamePieceType == GamePieceType.Cone ? GamePieceType.Cube : GamePieceType.Cone;
        elevator.setGamePieceType(gamePieceType);
        intake.setGamePieceType(gamePieceType);
    }

    @Override()
    public boolean isFinished(){
        return true;
    }
}
