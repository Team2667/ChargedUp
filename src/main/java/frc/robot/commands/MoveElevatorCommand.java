package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class MoveElevatorCommand extends CommandBase{
    XboxController controller;
    
    public MoveElevatorCommand(Elevator elevator, XboxController controller){
        addRequirements(elevator);
        this.controller = controller;
    }

    @Override
    public void execute(){
        // Rais and lower the elevator in response to dpad buttons:
        // https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/commands/ExtendCommand.java#L24
    }
}
