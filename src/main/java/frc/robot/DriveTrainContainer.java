package frc.robot;

import java.lang.Math;
import static java.lang.Math.PI;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DefaultDriveCommand;
import frc.robot.commands.DriveFieldRelative;
import frc.robot.commands.DriveTrainResetHeading;
import frc.robot.commands.GetInRangeOfTarget;
import frc.robot.commands.TurnToTarget;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import org.photonvision.PhotonCamera;
public class DriveTrainContainer {
    XboxController m_controller;
    DriveTrain dt_sub;

    DriveFieldRelative forwardCommand;
    DriveFieldRelative leftCommand;
    DriveFieldRelative backCommand;
    DriveFieldRelative rightCommand;
    GetInRangeOfTarget findAprilTag;
    TurnToTarget targetToTurn;
    PhotonCamera camera;

    // ToDo: add a turnToTarget command

    DefaultDriveCommand drivecmd;


    public DriveTrainContainer(XboxController controller, PhotonCamera camera) {
        if (isSubsystemEnabled()){
            this.m_controller = controller;
            dt_sub = new DriveTrain(camera);
            this.camera=camera;
            dt_sub.setDefaultCommand(new DefaultDriveCommand(dt_sub,
              () -> -modifyAxis(m_controller.getLeftY()),
              () -> modifyAxis(m_controller.getLeftX()),
              () -> -modifyAxis(m_controller.getRightX())
            ));
        }
       // createCommands();
       //     configureButtonBindings();
    }

    public boolean isSubsystemEnabled() {
        return Constants.DRIVE_TRAIN_ENABLED;
    }

    public void createCommands() {
      forwardCommand=new DriveFieldRelative(dt_sub, 0, 0.25);
      backCommand =new DriveFieldRelative(dt_sub, Math.PI, 0.25);
      leftCommand = new DriveFieldRelative(dt_sub, (2 * Math.PI * 3)/4, 0.25);
      rightCommand =new DriveFieldRelative(dt_sub, Math.PI / 2, 0.25);
    }

    public Command createDriveBackCommand() {
      return new DriveFieldRelative(dt_sub, PI, 0.25);
    }

    public Command createLeftCommand() {
      return new DriveFieldRelative(dt_sub, (Math.PI*3)/2, 0.25);
    }

    public Command createRightCommand() {
      return new DriveFieldRelative(dt_sub, Math.PI/2, 0.25);
    }

    public Command createForwardCommand() {
      return new DriveFieldRelative(dt_sub, 0, 0.25);
    }

    // TODO: Create a method that returns a RotateRobot command given an angle in Radians.

    public Command createResetHeadingCommand(){
      return new DriveTrainResetHeading(dt_sub);
    }

    private void configureButtonBindings() {
      
      JoystickButton forwardCommandButton=new JoystickButton(m_controller, XboxController.Button.kY.value);
      forwardCommandButton.whileTrue(forwardCommand);
      JoystickButton leftCommandButton=new JoystickButton(m_controller, XboxController.Button.kX.value);
      leftCommandButton.whileTrue(leftCommand);
      JoystickButton downCommandButton=new JoystickButton(m_controller, XboxController.Button.kA.value);
      downCommandButton.whileTrue(backCommand);
      JoystickButton rightCommandButton=new JoystickButton(m_controller, XboxController.Button.kB.value);
      rightCommandButton.whileTrue(rightCommand);
     /*  JoystickButton aprilTagButton=new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
      aprilTagButton.onTrue(findAprilTag);*/
      

      


      // todo Map the turnToTarget command to the right bumper that is activated when pressed (i.e. button.onTrue(turnToTargetCmd))
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
    public Command getBackComm() {
      return new DriveFieldRelative(dt_sub,Math.PI,Constants.AUTO_MAGNITUDE);
    }
}