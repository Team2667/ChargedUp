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
    private ZeroElevator zeroElevator;

    public ElevatorContainer(XboxController controller){
        elevator = new Elevator();
        moveElevatorCommand = new MoveElevatorCommand(elevator, controller);
        elevator.setDefaultCommand(moveElevatorCommand);
        zeroElevator=new ZeroElevator(elevator);
        JoystickButton zeroCommandButton=new JoystickButton(controller, XboxController.Button.kLeftStick.value);
        zeroCommandButton.onTrue(zeroElevator);
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

    public Command createElevatorGroundCommand() {
        return new Elevator2Position(elevator, GoalPosition.ground);
    }

    public Command createElevatorFeederCommand() {
        return new Elevator2Position(elevator, GoalPosition.feeder);
    }
    
}
