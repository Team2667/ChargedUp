package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.MoveElevatorCommand;
import frc.robot.subsystems.Elevator;

public class ElevatorContainer {
    private Elevator elevator;
    private XboxController controller;
    private Command moveElevatorCommand;

    public ElevatorContainer(XboxController controller){
        elevator = new Elevator();
        this.controller = controller;
        moveElevatorCommand = new MoveElevatorCommand(elevator, controller);
        elevator.setDefaultCommand(moveElevatorCommand);
    }


    //TODO: Implement the following

    public Command createElevatorHome(){
        return null;
    }

    public Command createElevatorLow() {
        return null;
    }

    public Command createElevatorMid() {
        return null;
    }

    public Command createElevatorHigh() {
        return null;
    }
    
}
