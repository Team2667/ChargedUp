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
        // If the left trigger is is pulled, suck game piece in.
        // If the right trigger is pulled, spit game piece out.
    }
    
}
