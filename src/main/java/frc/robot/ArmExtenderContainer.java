package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.ArmExtender;

public class ArmExtenderContainer {
    private XboxController controller;
    private ArmExtender armExtender;

    public ArmExtenderContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.controller = controller;
            // create arm extender
            // create commands
            // configure button bindings
        }

    }

    public boolean isSubsystemEnabled() {
        return Constants.PIVOT_ENABLED;
    }

    private void createCommands() {
    }

    private void configureButtonBindings() {
    }
}