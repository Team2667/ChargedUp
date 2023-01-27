package frc;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DriveFieldRelative;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;

public class DriveTrainContainer {
    XboxController m_controller;
    DriveTrain dt_sub;

    DriveFieldRelative forwardCommand;
    DriveFieldRelative leftCommand;
    DriveFieldRelative downCommand;
    DriveFieldRelative rightCommand;

    DefaultDriveCommand drivecmd;


    public DriveTrainContainer(XboxController controller) {
        if (isSubsystemEnabled()){
            this.m_controller = controller;
            dt_sub = new DriveTrain();
            dt_sub.setDefaultCommand(new DefaultDriveCommand(dt_sub,
              () -> -modifyAxis(m_controller.getLeftY()),
              () -> modifyAxis(m_controller.getLeftX()),
              () -> -modifyAxis(m_controller.getRightX())
            ));
            createCommands();
            configureButtonBindings();
        }
    }

    public boolean isSubsystemEnabled() {
        return Constants.DRIVE_TRAIN_ENABLED;
    }

    private void createCommands(){
      forwardCommand=new DriveFieldRelative(dt_sub,0,0.5);
      leftCommand=new DriveFieldRelative(dt_sub,1.57,0.5);
      downCommand=new DriveFieldRelative(dt_sub,3.14,0.5);
      rightCommand=new DriveFieldRelative(dt_sub,4.71,0.5);
    }

    private void configureButtonBindings() {
      JoystickButton forwardCommandButton=new JoystickButton(m_controller, XboxController.Button.kY.value);
      forwardCommandButton.whileTrue(forwardCommand);
      JoystickButton leftCommandButton=new JoystickButton(m_controller, XboxController.Button.kB.value);
      leftCommandButton.whileTrue(leftCommand);
      JoystickButton downCommandButton=new JoystickButton(m_controller, XboxController.Button.kA.value);
      downCommandButton.whileTrue(downCommand);
      JoystickButton rightCommandButton=new JoystickButton(m_controller, XboxController.Button.kX.value);
      rightCommandButton.whileTrue(rightCommand);
    }

    private double modifyAxis(double input)
    {
      input=deadband(input,0.05);
      // Square the axis
      return Math.copySign(input * input, input);
    }

    private static double deadband(double value,double deadband) {
        if (Math.abs(value)>deadband) {
          if (value>0.0) {
            return (value-deadband)/(1.0-deadband);
          } else {
            return (value+deadband)/(1.0-deadband);
          }
        } else {
          return 0.0;
        }
    }
}