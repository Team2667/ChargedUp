package frc;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DriveFieldRelative;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
            createCommands();
            configureButtonBindings();
        }
    }

    public boolean isSubsystemEnabled() {
        //TODO: Return the value of a constant defined in Constants
        return true;
    }

    // TODO: Add public getter methods to return commands needed for autonomous


    // TODO: Add code for creating the commands here
    private void createCommands(){
    }

    // TODO: Add code for binding buttons. Instead of calling whileHeld, call whileTrue
    private void configureButtonBindings() {
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