// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.photonvision.PhotonCamera;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import frc.DriveTrainContainer;
import frc.robot.subsystems.Pinchy;
import frc.robot.commands.CalibratePivot;
import frc.robot.commands.Pivot2Angle;
import frc.robot.subsystems.Pivot;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller = new XboxController(0);
  private PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000");
  // Dont remove
  private DriveTrainContainer driveTrainContainer;
  private JawsOfLifeContainer jolContainer;
  private PivotContainer pivotContainer;
  private ArmExtenderContainer armExtenderContainer;
  private PinchyContainer pinchyContainer;
  private Pivot pivot;
  

  public RobotContainer() {
    driveTrainContainer = new DriveTrainContainer(m_controller, camera);
    jolContainer = new JawsOfLifeContainer(m_controller);
    pivotContainer = new PivotContainer(m_controller);
    armExtenderContainer= new ArmExtenderContainer(m_controller);
    pinchyContainer = new PinchyContainer(m_controller);
    configButtonBindings();
  }

  public Command getAutonomousCommand() {
     return pivotContainer.getPivot2Med()
                         .andThen(armExtenderContainer.getExt2Med())
                         .andThen(jolContainer.toggleComand())
                         .andThen(armExtenderContainer.getExt2Home())
                         .andThen(jolContainer.toggleComand())
                         .andThen(pivotContainer.getPivot2Home())
                         .andThen(driveTrainContainer.getBackComm().withTimeout(Constants.AUTO_RUN_TIME));
   
  }

  public void configButtonBindings(){

    if (pivotContainer.isSubsystemEnabled()){
      JoystickButton lowCommand = new JoystickButton(m_controller, XboxController.Button.kX.value);
      JoystickButton medCommand = new JoystickButton(m_controller, XboxController.Button.kY.value);
      JoystickButton highCommand = new JoystickButton(m_controller, XboxController.Button.kB.value);
      JoystickButton homeCommand = new JoystickButton(m_controller, XboxController.Button.kA.value);
      JoystickButton objectFromFeeder = new JoystickButton(m_controller, XboxController.Button.kRightStick.value);
      JoystickButton toggleGamePiece = new JoystickButton(m_controller, XboxController.Button.kLeftStick.value);
      lowCommand.onTrue(armExtenderContainer.getExt2Home()
                        .andThen(pivotContainer.getPivot2Low())
                        .andThen(armExtenderContainer.getExt2Low()));

      medCommand.onTrue(armExtenderContainer.getExt2Home()
                        .andThen(pivotContainer.getPivot2Med()
                        .andThen(armExtenderContainer.getExt2Med())));

      highCommand.onTrue(armExtenderContainer.getExt2Home()
                        .andThen(pivotContainer.getPivot2High()
                        .andThen(armExtenderContainer.getExt2High())));

      homeCommand.onTrue(armExtenderContainer.getExt2Home()
                        .andThen(pivotContainer.getPivot2Home()
                        .andThen(armExtenderContainer.getExt2Home())));
      
      toggleGamePiece.onTrue(pivotContainer.getToggleGamePieceCommand());

      objectFromFeeder.onTrue(armExtenderContainer.getExt2Home()
                              .andThen(pivotContainer.getPivot2Feeder()
                              .andThen(armExtenderContainer.getExtendToFeeder())));
    }
  }
}
