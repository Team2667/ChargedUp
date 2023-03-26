// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import java.lang.System;

import org.photonvision.PhotonCamera;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.GamePieceType;
import frc.robot.commands.DriveTrainResetHeading;
import frc.robot.commands.SetMode;
import frc.robot.commands.ToggleConesCubes;
import frc.robot.subsystems.Elevator;
import static frc.robot.Constants.GamePieceType.Cube;

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
  private SendableChooser<Command> mailman;
  


  public RobotContainer() {
    driveTrainContainer = new DriveTrainContainer(m_controller, camera);
    elevatorContainer = new ElevatorContainer(m_controller);//They de-frenched it
    wristContainer=new WristContainer(m_controller);
    intakeContainer = new IntakeContainer(m_controller);
    createButtonBindings();
    mailman=new SendableChooser<Command>();
    populateMailbox();
    SmartDashboard.putData("autonomous mode", mailman);
  }



public Command CreateSetMode(GamePieceType gamePieceType)
{
  return new SetMode(elevatorContainer.elevator, intakeContainer.intake, gamePieceType);
}
  public Command getAutonomousCommand(){
    return mailman.getSelected();
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
  //  xButton.onTrue(wristContainer.createWristInCommand().andThen(elevatorContainer.createElevatorLow()));
    yButton.onTrue(wristContainer.createWristInCommand().andThen(elevatorContainer.createElevatorMid()));
    bButton.onTrue(wristContainer.createWristOutCommand().alongWith(elevatorContainer.createElevatorHigh()));
    aButton.onTrue(wristContainer.createWristInCommand().alongWith(elevatorContainer.createElevatorHome()));
    leftBumper.onTrue(CreateSetMode(GamePieceType.Cube).andThen(wristContainer.createWristInCommand().alongWith(elevatorContainer.createElevatorSlideCommand())));
    rightBumper.onTrue(CreateSetMode(GamePieceType.Cube).andThen(wristContainer.createWristOutCommand()).andThen(elevatorContainer.createElevatorLow()));
  }
  private void populateMailbox()
  {
    mailman.setDefaultOption("deploy cube only",
    elevatorContainer.create0ElevatorCommand()
    .andThen(CreateSetMode(Cube))
    .andThen(wristContainer.createWristInCommand())
    .andThen(elevatorContainer.createElevatorSlideCommand())
    .andThen(intakeContainer.createIntakeOutCommand().withTimeout(1))
     );

     mailman.addOption("Deploy Cube, Go Left",
     elevatorContainer.create0ElevatorCommand()
    .andThen(CreateSetMode(Cube))
    .andThen(wristContainer.createWristInCommand())
    .andThen(elevatorContainer.createElevatorSlideCommand())
    .andThen(intakeContainer.createIntakeOutCommand().withTimeout(1))
    .andThen(driveTrainContainer.createLeftCommand().withTimeout(.75))
    .andThen(driveTrainContainer.createDriveBackCommand().withTimeout(5.0))
    );

     mailman.addOption("Deploy Cube, Go Right",  elevatorContainer.create0ElevatorCommand()
     .andThen(CreateSetMode(Cube))
     .andThen(wristContainer.createWristInCommand())
     .andThen(elevatorContainer.createElevatorSlideCommand())
     .andThen(intakeContainer.createIntakeOutCommand().withTimeout(1))
     .andThen(driveTrainContainer.createRightCommand().withTimeout(.75))
     .andThen(driveTrainContainer.createDriveBackCommand().withTimeout(5.0))
     );

  }
}