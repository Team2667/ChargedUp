// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveFieldRelative;
import frc.robot.commands.JawsCommand;
import frc.robot.subsystems.JawsOfLife;
import frc.DriveTrainContainer;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller=new XboxController(0);
 //Dont remove 
 private DriveTrainContainer driveTrainContainer;
  public RobotContainer() {
     driveTrainContainer = new DriveTrainContainer(m_controller);
    JawsOfLife jawsOfLifeSubsystem = new JawsOfLife();
    JawsCommand jawsCommand = new JawsCommand(jawsOfLifeSubsystem);
    // jawsOfLifeSubsystem.setDefaultCommand(jawsCommand);
    JoystickButton testToggleButton = new JoystickButton(m_controller, XboxController.Button.kRightBumper.value);
testToggleButton.toggleOnTrue(jawsCommand);
 

  }

  public Command getAutonomousCommand() {
    return null;
    // An ExampleCommand will run in autonomous
  }
}
