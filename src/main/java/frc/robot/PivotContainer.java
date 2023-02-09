package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.subsystems.Pivot;

public class PivotContainer {
    private XboxController controller;
    private Pivot pivot;

    public PivotContainer(XboxController controller){
        if (isSubsystemEnabled()){
            this.controller = controller;
            // create the subsystem
            // create the commands
            // configure the button bindings
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
