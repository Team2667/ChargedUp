package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExtendCommand;
import frc.robot.subsystems.ArmExtender;

public class ArmExtenderContainer {
    private XboxController controller;
    private ArmExtender armExtender;
    private ExtendCommand extendCommand;

    public ArmExtenderContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.controller = controller;
            armExtender=new ArmExtender();
            createCommands();
            armExtender.setDefaultCommand(extendCommand);
            /* create arm extender
             create commands
             configure button bindings */
        }

    }

    public boolean isSubsystemEnabled() {
        return Constants.PIVOT_ENABLED;
    }

    private void createCommands() {
        extendCommand=new ExtendCommand(armExtender, controller);
    }

    private void configureButtonBindings() {
    }
}
