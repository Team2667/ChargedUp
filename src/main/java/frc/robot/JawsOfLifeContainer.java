package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.JawsCommand;
import frc.robot.subsystems.JawsOfLife;

public class JawsOfLifeContainer {
    JawsOfLife jawsOfLifeSubsystem;
    JawsCommand jawsCommand;
    XboxController controller;

    public JawsOfLifeContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.controller = controller;
            jawsOfLifeSubsystem  = new JawsOfLife();
            createCommands();
            configureButtonBindings();
        }
    }

    public boolean isSubsystemEnabled() {
        return Constants.JAWS_OF_LIFE_ENABLED;
    }

    private void createCommands() {
        jawsCommand = new JawsCommand(jawsOfLifeSubsystem);
    }

    private void configureButtonBindings() {
        JoystickButton testToggleButton = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
        testToggleButton.toggleOnTrue(jawsCommand);
    }
    public Command toggleComand(){
        return new JawsCommand(jawsOfLifeSubsystem);
    }
}
 