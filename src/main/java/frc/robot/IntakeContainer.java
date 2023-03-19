package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.IntakeDefaultCommand;
import frc.robot.subsystems.Intake;

public class IntakeContainer {
    public Intake intake;
    private IntakeDefaultCommand intakeCmd;

    public IntakeContainer(XboxController controller){
        intake = new Intake();
        intakeCmd = new IntakeDefaultCommand(intake, controller);
        intake.setDefaultCommand(intakeCmd);
    }
}
