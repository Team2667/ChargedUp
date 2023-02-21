package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.PinchyCommand;
import frc.robot.subsystems.Pinchy;

public class PinchyContainer {
    private XboxController controller;
    private PinchyCommand pinchyCommand;
    private Pinchy pinchy;

    public PinchyContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.controller = controller;
            pinchy=new Pinchy();
            createCommands();
        }
    }
    
    public boolean isSubsystemEnabled() {
        return Constants.PINCH_ENABLED;
    }

    private void createCommands() {
        pinchyCommand=new PinchyCommand(controller,pinchy);
        pinchy.setDefaultCommand(pinchyCommand);
    }

    private void configureButtonBindings() {
    }
}
