package frc.robot.commands;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Pinchy;
//This code was taken from ball grabber
public class PinchyCommand extends CommandBase {
    Pinchy PinchySub;
    XboxController joy;
    public PinchyCommand(XboxController controller, Pinchy PinchySub) {
        this.PinchySub=PinchySub;
        this.setSubsystem("PinchySub");
        this.addRequirements(PinchySub);
        this.joy = controller;
    }

    @Override
    public void initialize() {
        //PinchySub.forward(0.01 * Constants.PINCHY_PERCENTAGE);
    }

    @Override
    public void execute(){
        var pinchySpeed = joy.getLeftTriggerAxis() != 0 ? joy.getLeftTriggerAxis() : -joy.getRightTriggerAxis();

        PinchySub.setPinchySpeed(pinchySpeed * (Constants.PINCHY_PERCENTAGE/100));
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interruption) {
        PinchySub.off();
    }
}
