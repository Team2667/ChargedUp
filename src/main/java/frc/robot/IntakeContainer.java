package frc.robot;

import java.lang.ModuleLayer.Controller;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.IntakeDefaultCommand;
import frc.robot.commands.IntakeOutCommand;
import frc.robot.subsystems.Intake;

public class IntakeContainer {
    public Intake intake;
    private IntakeDefaultCommand intakeCmd;
    private XboxController controller;

    public IntakeContainer(XboxController controller){
        intake = new Intake();
        intakeCmd = new IntakeDefaultCommand(intake, controller);
        intake.setDefaultCommand(intakeCmd);
        controller=this.controller;
    }

    public Command createIntakeOutCommand(){
        return new IntakeOutCommand(intake);
    }
    public Command createIntakeDefaultCommand()
    {
        return new IntakeDefaultCommand(intake, controller);
    }
}
