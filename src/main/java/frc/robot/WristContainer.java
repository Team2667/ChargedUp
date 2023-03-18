package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.MoveElevatorCommand;
import frc.robot.commands.WristDefaultCommand;
import frc.robot.commands.WristIn;
import frc.robot.commands.WristOut;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Wrist;

public class WristContainer {
    private Wrist wrist;
    private XboxController controller;
    private Command wristDefaultCommand;

    public WristContainer(XboxController controller){
        wrist = new Wrist();
        this.controller = controller;
        wristDefaultCommand = new WristDefaultCommand(wrist, controller);
        wrist.setDefaultCommand(wristDefaultCommand);
    }

    public Command getWristOutCommand(){
        return new WristOut(wrist);
    }

    public Command getWristCommand(){
        return new WristIn(wrist);
    }
}
