package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.IntakeDefaultCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.subsystems.Intake;

public class IntakeContainer {
    public Intake intake;
    private IntakeDefaultCommand intakeCmd;

    public IntakeContainer(XboxController controller){
        intake = new Intake();
        intakeCmd = new IntakeDefaultCommand(intake, controller);
        intake.setDefaultCommand(intakeCmd);
    }

    public Command createIntakeOutCommand(){
        return new IntakeOutCommand(intake);
    }
}
