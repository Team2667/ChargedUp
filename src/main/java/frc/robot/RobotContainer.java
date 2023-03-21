// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.lang.System;

import org.photonvision.PhotonCamera;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.GamePieceType;
import frc.robot.commands.DriveTrainResetHeading;
import frc.robot.commands.SetMode;
import frc.robot.commands.ToggleConesCubes;
import frc.robot.subsystems.Elevator;
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
  private PhotonCamera camera = new PhotonCamera("USB_webcam");
  private DriveTrainContainer driveTrainContainer;
  private ElevatorContainer elevatorContainer;
  private IntakeContainer intakeContainer;
  private WristContainer wristContainer;


  public RobotContainer() {
    driveTrainContainer = new DriveTrainContainer(m_controller, camera);
    elevatorContainer = new ElevatorContainer(m_controller);//They de-frenched it
    wristContainer=new WristContainer(m_controller);
    intakeContainer = new IntakeContainer(m_controller);
    createButtonBindings();
  }
public Command CreateSetMode(GamePieceType gamePieceType)
{
  return new SetMode(elevatorContainer.elevator, intakeContainer.intake, gamePieceType);
}
  public Command getAutonomousCommand(){
    return elevatorContainer.create0ElevatorCommand()
                         .andThen(CreateSetMode(GamePieceType.Cube))
                         .andThen(wristContainer.createWristInCommand())
                         .andThen(elevatorContainer.createElevatorSlideCommand())
                         .andThen(intakeContainer.createIntakeOutCommand().withTimeout(1))
                         .andThen(driveTrainContainer.createDriveBackCommand().withTimeout(3));
  }

  public void createButtonBindings(){
    var startButton = new JoystickButton(m_controller, XboxController.Button.kStart.value);
    var backButton = new JoystickButton(m_controller, XboxController.Button.kBack.value);
    var xButton = new JoystickButton(m_controller, XboxController.Button.kX.value);
    var yButton = new JoystickButton(m_controller, XboxController.Button.kY.value);
    var bButton = new JoystickButton(m_controller, XboxController.Button.kB.value);
    var aButton = new JoystickButton(m_controller, XboxController.Button.kA.value);
    var leftBumper = new JoystickButton(m_controller, XboxController.Button.kLeftBumper.value);
    var rightBumper = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
    var rightStick = new JoystickButton(m_controller, XboxController.Button.kRightStick.value);

    backButton.onTrue(elevatorContainer.create0ElevatorCommand());
    rightStick.onTrue(driveTrainContainer.createResetHeadingCommand());
    xButton.onTrue(wristContainer.createWristOutCommand().andThen(elevatorContainer.createElevatorLow()));
    yButton.onTrue(wristContainer.createWristOutCommand().andThen(elevatorContainer.createElevatorMid()));
    bButton.onTrue(wristContainer.createWristOutCommand().andThen(elevatorContainer.createElevatorHigh()));
    aButton.onTrue(wristContainer.createWristInCommand().andThen(elevatorContainer.createElevatorHome()));
    leftBumper.onTrue(CreateSetMode(GamePieceType.Cube).andThen(wristContainer.createWristInCommand()).andThen(elevatorContainer.createElevatorSlideCommand()));
    rightBumper.onTrue(CreateSetMode(GamePieceType.Cone).andThen(wristContainer.createWristOutCommand()).andThen(elevatorContainer.createElevatorFeederCommand()));
  }
}