package frc.robot;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.PivotCommand;
import frc.robot.commands.PivotToggleGamePiece;
import frc.robot.subsystems.Pivot;
import frc.robot.commands.Pivot2Angle;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.HoldInPlace;
import frc.robot.commands.CalibratePivot;

public class PivotContainer {
    private XboxController controller;
    private Pivot pivot;
    private PivotCommand pivotCommand;
    private Pivot2Angle pivot2Home;
    private Pivot2Angle pivot2Feeder;
    private Pivot2Angle pivot2Low;
    private Pivot2Angle pivot2Med;
    private Pivot2Angle pivot2High;
    private HoldInPlace holdInPlace;
    private PivotToggleGamePiece toggleGamePiece;
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
        holdInPlace = new HoldInPlace(pivot);
    }

    public Command getPivot2Low() {
        return new Pivot2Angle(pivot, Constants.GoalPos.low);
    }
    public Command getPivot2Med() {
        return new Pivot2Angle(pivot, Constants.GoalPos.med);
    }
    public Command getPivot2High() {
        return new Pivot2Angle(pivot, Constants.GoalPos.high);
    }
    public Command getPivot2Home() {
        return new Pivot2Angle(pivot, Constants.GoalPos.home);
    }

    public Command getPivot2Feeder(){
        return new Pivot2Angle(pivot, Constants.GoalPos.feeder);
    }

    public Command getCalibratePivot() {
        return new CalibratePivot(pivot);
    }

    public Command getToggleGamePieceCommand(){
        return new PivotToggleGamePiece(pivot);
    }

    private void configureButtonBindings() {
        JoystickButton startCmdButton = new JoystickButton(controller, XboxController.Button.kStart.value);
        startCmdButton.whileTrue(pivotCommand);
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
