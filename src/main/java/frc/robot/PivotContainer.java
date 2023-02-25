package frc.robot;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PivotCommand;
import frc.robot.subsystems.Pivot;
import frc.robot.commands.Pivot2Angle;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.commands.CalibratePivot;

public class PivotContainer {
    private XboxController controller;
    private Pivot pivot;
    private PivotCommand pivotCommand;
    private Pivot2Angle pivot2Home;
    private Pivot2Angle pivot2Low;
    private Pivot2Angle pivot2Med;
    private Pivot2Angle pivot2High;
    private CalibratePivot calibPivot;
    public PivotContainer(XboxController controller) {
        if (isSubsystemEnabled()) {
            this.controller = controller;
            pivot = new Pivot();
            createCommands();
            configureButtonBindings();
            // create the subsystem
            // create the commands
            // configure the button bindings
        }
    }

    public boolean isSubsystemEnabled() {
        return Constants.PIVOT_ENABLED;
    }

    private void createCommands() {
        pivotCommand = new PivotCommand(pivot, controller);
        pivot.setDefaultCommand(pivotCommand);
        pivot2Low = new Pivot2Angle(pivot, Constants.PIVOT_ROT_LOW);
        pivot2Med = new Pivot2Angle(pivot, Constants.PIVOT_ROT_MEDIUM);
        pivot2High = new Pivot2Angle(pivot, Constants.PIVOT_ROT_HIGH);
        pivot2Home = new Pivot2Angle(pivot, Constants.PIVOT_ROT_HOME);

        calibPivot = new CalibratePivot(pivot);
    }

    public Command getPivot2Low() {
        return pivot2Low;
    }
    public Command getPivot2Med() {
        return pivot2Med;
    }
    public Command getPivot2High() {
        return pivot2High;
    }
    public Command getPivot2Home() {
        return pivot2Home;
    }

    public Command getCalibratePivot() {
        return calibPivot;
    }

    private void configureButtonBindings() {
        
        /*JoystickButton forwardCommandButton = new JoystickButton(controller, XboxController.Button.kX.value);
        forwardCommandButton.onTrue(pivot2Low);
        JoystickButton leftCommandButton = new JoystickButton(controller, XboxController.Button.kY.value);
        leftCommandButton.onTrue(pivot2Med);
        JoystickButton downCommandButton = new JoystickButton(controller, XboxController.Button.kB.value);
        downCommandButton.onTrue(pivot2High);
        JoystickButton rightCommandButton = new JoystickButton(controller, XboxController.Button.kA.value);
        rightCommandButton.onTrue(pivot2Home);*/
    }
}
