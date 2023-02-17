package frc.robot.subsystems;
import com.kauailabs.navx.frc.*;
import static frc.robot.Constants.*;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.swerveSupport.SwerveModule;
import frc.robot.subsystems.swerveSupport.SwerveModuleConfiguration;
import frc.robot.utils.KnightsCameraUtils;

import org.photonvision.PhotonCamera;
public class TempDT extends SubsystemBase {
    private final AHRS m_navx = new AHRS(SPI.Port.kMXP, (byte) 200); // NavX connected over MXP

    private PhotonCamera camera;
    private final SwerveModule m_frontLeftModule;
    private final SwerveModule m_frontRightModule;
    private final SwerveModule m_backLeftModule;
    private final SwerveModule m_backRightModule;
    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
                            (14.0 / 50.0) * (25.0 / 19.0) * (15.0 / 45.0) * 0.10033 * Math.PI;

    SwerveDrivePoseEstimator m_PoseEstimator;
    KnightsCameraUtils camperaUtils;

    public static final double MAX_VOLTAGE = 12.0;

    private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
        // Front left
        new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
        // Front right
        new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
        // Back left
        new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
        // Back right
        new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)
    );

    public Rotation2d getGyroscopeRotation() {    
        SmartDashboard.putBoolean("MagnetomitorCalibration", m_navx.isMagnetometerCalibrated());
        // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
       // return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
        return Rotation2d.fromDegrees(m_navx.getFusedHeading());
    }
      
    public DriveTrain(PhotonCamera camera){
        this.camera=camera;

        m_navx.calibrate();

        m_frontLeftModule = new SwerveModule(SwerveModuleConfiguration.frontLeftConfig());

        m_frontRightModule = new SwerveModule(SwerveModuleConfiguration.frontRightConfig());

        m_backLeftModule  = new SwerveModule(SwerveModuleConfiguration.backLeftConfig());

        m_backRightModule = new SwerveModule(SwerveModuleConfiguration.backRightConfig());

        m_PoseEstimator = new SwerveDrivePoseEstimator(m_kinematics, 
            getGyroscopeRotation(), getSwerveModulePositions(), new Pose2d());
    }

    public void drive (ChassisSpeeds chassisSpeeds){
        m_chassisSpeeds = chassisSpeeds;
        SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
        SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

        m_frontLeftModule.setDesiredState(states[2]);
        m_frontRightModule.setDesiredState(states[3]);
        m_backLeftModule.setDesiredState(states[0]);
        m_backRightModule.setDesiredState(states[1]);
    }

    public void moveFieldRelative(double speedMetersPerSecond, double angleInRadians) {
      var moduleState = new SwerveModuleState(speedMetersPerSecond, new Rotation2d(angleInRadians));
      m_frontLeftModule.setDesiredState(moduleState);
      m_frontRightModule.setDesiredState(moduleState);
      m_backRightModule.setDesiredState(moduleState);
      m_backLeftModule.setDesiredState(moduleState);
    }

    public void stop() {
        m_frontLeftModule.stop();
        m_frontRightModule.stop();
        m_backRightModule.stop();
        m_backLeftModule.stop();
    }

    @Override
    public void periodic() {
        m_frontLeftModule.outputSteerAnglesToDashboard();
        m_frontRightModule.outputSteerAnglesToDashboard();
        m_backLeftModule.outputSteerAnglesToDashboard();
        m_backRightModule.outputSteerAnglesToDashboard();
        SmartDashboard.putNumber("NavX: ", m_navx.getFusedHeading());
        var latestResult=camera.getLatestResult();
        if (latestResult.hasTargets()) {
            SmartDashboard.putNumber("AprilTag: ", latestResult.getBestTarget().getFiducialId());
            SmartDashboard.putNumber("Yaw", latestResult.getBestTarget().getYaw());
        }
        else {
            SmartDashboard.putNumber("AprilTag: ", 69);
            SmartDashboard.putNumber("Yaw", 420);
        }
    }

    public void updateOdometry() {
        m_PoseEstimator.update(getGyroscopeRotation(), getSwerveModulePositions());

        
        m_poseEstimator.update(
                m_gyro.getRotation2d(), m_leftEncoder.getDistance(), m_rightEncoder.getDistance());

        // Also apply vision measurements. We use 0.3 seconds in the past as an example
        // -- on
        // a real robot, this must be calculated based either on latency or timestamps.
        Optional<EstimatedRobotPose> result =
                pcw.getEstimatedGlobalPose(m_poseEstimator.getEstimatedPosition());

        if (result.isPresent()) {
            EstimatedRobotPose camPose = result.get();
            m_poseEstimator.addVisionMeasurement(
                    camPose.estimatedPose.toPose2d(), camPose.timestampSeconds);
            m_fieldSim.getObject("Cam Est Pos").setPose(camPose.estimatedPose.toPose2d());
        } else {
            // move it way off the screen to make it disappear
            m_fieldSim.getObject("Cam Est Pos").setPose(new Pose2d(-100, -100, new Rotation2d()));
        }

        m_fieldSim.getObject("Actual Pos").setPose(m_drivetrainSimulator.getPose());
        m_fieldSim.setRobotPose(m_poseEstimator.getEstimatedPosition());
    }

    public SwerveModulePosition[] getSwerveModulePositions() {
        SwerveModulePosition modulePositions[] =
        {
            new SwerveModulePosition(m_frontLeftModule.getWheelPosition(), Rotation2d.fromRadians(m_frontLeftModule.getAbsoluteAngle())),
            new SwerveModulePosition(m_frontRightModule.getWheelPosition(), Rotation2d.fromRadians(m_frontRightModule.getAbsoluteAngle())),
            new SwerveModulePosition(m_backLeftModule.getWheelPosition(), Rotation2d.fromRadians(m_backLeftModule.getAbsoluteAngle())),
            new SwerveModulePosition(m_backRightModule.getWheelPosition(), Rotation2d.fromRadians(m_backRightModule.getAbsoluteAngle()))
        };
        return modulePositions;
    }
}