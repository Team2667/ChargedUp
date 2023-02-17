package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.PivotCommand;
import frc.robot.subsystems.Pivot;

public class PivotContainer {
    private XboxController controller;
    private Pivot pivot;
    private PivotCommand pivotCommand;

    public PivotContainer(XboxController controller){
        if (isSubsystemEnabled()){
            this.controller = controller;
            pivot=new Pivot();
            createCommands();
            // create the subsystem
            // create the commands
            // configure the button bindings
        }
    }
    
    public boolean isSubsystemEnabled() {
        return Constants.PIVOT_ENABLED;
    }

    private void createCommands() {
        pivotCommand=new PivotCommand(pivot, controller);
        pivot.setDefaultCommand(pivotCommand);
    }

    private void configureButtonBindings() {
    }
}
