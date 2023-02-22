package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExtendCommand;
import frc.robot.subsystems.ArmExtender;
import frc.robot.commands.Protrude2Angle;
import edu.wpi.first.wpilibj2.command.Command;

public class ArmExtenderContainer {
    private XboxController controller;
    private ArmExtender armExtender;
    private ExtendCommand extendCommand;
    public Protrude2Angle ext2Home;
    public Protrude2Angle ext2Low;
    public Protrude2Angle ext2Med;
    public Protrude2Angle ext2High;

    public ArmExtenderContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.controller = controller;
            armExtender=new ArmExtender();
            createCommands();
            configureButtonBindings();
            armExtender.setDefaultCommand(extendCommand);
        }

    }

    public boolean isSubsystemEnabled() {
        return Constants.PIVOT_ENABLED;
    }

    private void createCommands() {
        extendCommand=new ExtendCommand(armExtender, controller);
        ext2Low = new Protrude2Angle(armExtender, Constants.PIVOT_ROT_LOW);
        ext2Med = new Protrude2Angle(armExtender, Constants.PIVOT_ROT_MEDIUM);
        ext2High = new Protrude2Angle(armExtender, Constants.PIVOT_ROT_HIGH);
        ext2Home = new Protrude2Angle(armExtender, Constants.PIVOT_ROT_HOME);
    }

    public Command getExt2Low() {
        return ext2Low;
    }
    public Command getExt2Med() {
        return ext2Med;
    }
    public Command getExt2High() {
        return ext2High;
    }
    public Command getExt2Home() {
        return ext2Home;
    }
    private void configureButtonBindings() {
        /*JoystickButton leftCommandButton = new JoystickButton(controller, XboxController.Button.kY.value);
        leftCommandButton.onTrue(ext2Low);*/
    }
}