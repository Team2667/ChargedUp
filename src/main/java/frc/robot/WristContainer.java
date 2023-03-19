package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.WristMoveCommand;
import frc.robot.commands.WristIn;
import frc.robot.commands.WristOut;
import frc.robot.subsystems.Wrist;

public class WristContainer {
    private Wrist wrist;
    private Command wristDefaultCommand;

    public WristContainer(XboxController controller){
        wrist = new Wrist();
        wristDefaultCommand = new WristMoveCommand(wrist, controller);
        wrist.setDefaultCommand(wristDefaultCommand);
    }

    public Command createWristOutCommand(){
        return new WristOut(wrist);
    }

    public Command createWristInCommand(){
        return new WristIn(wrist);
    }
}
