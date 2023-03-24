package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeOutCommand extends CommandBase{
    private Intake intake;

    public IntakeOutCommand(Intake intake){
        this.intake = intake;
        addRequirements(intake);
    }

    @Override()
    public void initialize(){
        intake.set(1);
    }

    @Override()
    public void end(boolean interrupted){
        intake.stop();
    }
}
