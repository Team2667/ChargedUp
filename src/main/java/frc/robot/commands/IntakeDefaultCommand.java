package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeDefaultCommand extends CommandBase{
    private Intake intake;
    private XboxController controller;

    public IntakeDefaultCommand(Intake intake, XboxController controller) {
        this.intake = intake;
        this.controller = controller;
        addRequirements(intake);
    }

    @Override
    public void execute(){
        System.out.println("!!!!!!!!!!!!!!" + controller.getLeftTriggerAxis());
       intake.set(controller.getLeftTriggerAxis());
    }
    
}
