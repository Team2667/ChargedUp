package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DriveFieldRelative;
import frc.robot.subsystems.DriveTrain;

public class DriveTrainContainer {
    private final XboxController m_controller=new XboxController(0);

  // The robot's subsystems and commands are defined here...
  DriveFieldRelative forwardCommand;
  DriveFieldRelative leftCommand;
  DriveFieldRelative downCommand;
  DriveFieldRelative rightCommand;
  DefaultDriveCommand drivecmd;

  public DriveTrainContainer() {
    setupDriveTrain();
    configureButtonBindings();
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

  private double modifyAxis(double input)
  {
    input=deadband(input,0.05);
    // Square the axis
    return Math.copySign(input * input, input);
  }

  private void setupDriveTrain()
  {
    DriveTrain dt_sub=new DriveTrain();
    dt_sub.setDefaultCommand(new DefaultDriveCommand(dt_sub,
        () -> -modifyAxis(m_controller.getLeftY()),
        () -> -modifyAxis(m_controller.getLeftX()),
        () -> -modifyAxis(m_controller.getRightX())
    ));

    // TODO: name these numbers!
    forwardCommand=new DriveFieldRelative(dt_sub,0,0.5);
    leftCommand=new DriveFieldRelative(dt_sub,1.57,0.5);
    downCommand=new DriveFieldRelative(dt_sub,3.14,0.5);
    rightCommand=new DriveFieldRelative(dt_sub,4.71,0.5);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton forwardCommandButton=new JoystickButton(m_controller, XboxController.Button.kY.value);
    forwardCommandButton.whileHeld(forwardCommand);
    JoystickButton leftCommandButton=new JoystickButton(m_controller, XboxController.Button.kB.value);
    leftCommandButton.whileHeld(leftCommand);
    JoystickButton downCommandButton=new JoystickButton(m_controller, XboxController.Button.kA.value);
    downCommandButton.whileHeld(downCommand);
    JoystickButton rightCommandButton=new JoystickButton(m_controller, XboxController.Button.kX.value);
    rightCommandButton.whileHeld(rightCommand);
  }
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
    // An ExampleCommand will run in autonomous
  }
}
