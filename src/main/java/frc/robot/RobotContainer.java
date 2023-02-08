// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.photonvision.PhotonCamera;
//import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
//import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.trajectory.Trajectory;
import frc.DriveTrainContainer;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller=new XboxController(0);
  private PhotonCamera camera = new PhotonCamera("Microsoft_LifeCam_HD-3000"); //Used to be called "March_May". I weep that my creativity is wasted. :(
  private DriveTrainContainer driveTrainContainer; //Dont remove 

  public RobotContainer() {
    driveTrainContainer = new DriveTrainContainer(m_controller,camera);
  }

  public Command getAutonomousCommand() {
    // 1. Create trajectory settings
    return null;
  }
}