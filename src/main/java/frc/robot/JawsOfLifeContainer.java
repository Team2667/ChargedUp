package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.JawsCommand;
import frc.robot.subsystems.JawsOfLife;

public class JawsOfLifeContainer {
    JawsOfLife jawsOfLifeSubsystem;
    JawsCommand jawsCommand;
    XboxController controller;

    public JawsOfLifeContainer(XboxController controller) {
        this.controller = controller;
        jawsOfLifeSubsystem  = new JawsOfLife();
        createCommands();
        configureButtonBindings();
    }

    private void createCommands() {
        JawsCommand jawsCommand = new JawsCommand(jawsOfLifeSubsystem);
    }

    private void configureButtonBindings() {
        JoystickButton testToggleButton = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
        testToggleButton.toggleOnTrue(jawsCommand);
    }
}
