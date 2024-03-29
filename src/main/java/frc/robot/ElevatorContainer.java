package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Elevator2Position;
import frc.robot.commands.MoveElevatorCommand;
import frc.robot.commands.ZeroElevator;
import frc.robot.subsystems.Elevator;
import static frc.robot.Constants.GoalPosition;

public class ElevatorContainer {
    public Elevator elevator;
    private Command moveElevatorCommand;

    public ElevatorContainer(XboxController controller){
        elevator = new Elevator();
        moveElevatorCommand = new MoveElevatorCommand(elevator, controller);
        elevator.setDefaultCommand(moveElevatorCommand);
    }


    public Command createElevatorHome(){
        return new Elevator2Position(elevator, GoalPosition.home);
    }

    public Command createElevatorLow() {
        return new Elevator2Position(elevator, GoalPosition.low);
    }

    public Command createElevatorMid() {
        return new Elevator2Position(elevator, GoalPosition.mid);
    }

    public Command createElevatorHigh() {
        return new Elevator2Position(elevator, GoalPosition.high);
    }

    public Command createElevatorSlideCommand() {
        return new Elevator2Position(elevator, GoalPosition.slide);
    }
    

    public Command createElevatorFeederCommand() {
        return new Elevator2Position(elevator, GoalPosition.feeder);
    }

    public Command create0ElevatorCommand() {
        return new ZeroElevator(elevator);
    }
    
}
