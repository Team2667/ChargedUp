package frc.robot.subsystems;
import com.kauailabs.navx.frc.*;
import static frc.robot.Constants.*;
import java.lang.Math;
import java.util.Optional;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.swerveSupport.SwerveModule;
import frc.robot.subsystems.swerveSupport.SwerveModuleConfiguration;
import frc.robot.utils.PhotonCameraWrapper;
import com.ctre.phoenix.sensors.Pigeon2;
import com.ctre.phoenix.sensors.Pigeon2Configuration;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
public class DriveTrain extends SubsystemBase {

    Pigeon2 pigeon2;
    private PhotonCameraWrapper cameraWrapper;
    private final SwerveModule m_frontLeftModule;
    private final SwerveModule m_frontRightModule;
    private final SwerveModule m_backLeftModule;
    private final SwerveModule m_backRightModule;
    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);
    SwerveDrivePoseEstimator m_PosEstimator;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
                            (14.0 / 50.0) * (25.0 / 19.0) * (15.0 / 45.0) * 0.10033 * Math.PI;
    private double headingOffset = 0;

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

    public Rotation2d getGyroscopeRotation() 
    {

        double yaww=pigeon2.getYaw()%360;
        if(yaww<0){
            yaww+=360;
        }
        yaww=360-yaww;
        return Rotation2d.fromDegrees(yaww - headingOffset);
    }

    public void setRotationalOffsetToCurrent(){
        double yaww=pigeon2.getYaw()%360;
        if(yaww<0){
            yaww+=360;
        }
        yaww=360-yaww;
        headingOffset = yaww;
    }
      
    public DriveTrain(PhotonCamera camera)
    {
        this.cameraWrapper = new PhotonCameraWrapper(camera);
        pigeon2 = new Pigeon2(Constants.pigeon);
        Pigeon2Configuration pigeon_conf = new Pigeon2Configuration();

        pigeon_conf.MountPosePitch = 0.0;
        pigeon_conf.MountPoseRoll = 0.0;
        pigeon_conf.MountPoseYaw = 0.0;
        pigeon_conf.EnableCompass=false;
        pigeon2.configAllSettings(pigeon_conf);
    

        m_frontLeftModule = new SwerveModule(SwerveModuleConfiguration.frontLeftConfig());
        m_frontRightModule = new SwerveModule(SwerveModuleConfiguration.frontRightConfig());
        m_backLeftModule  = new SwerveModule(SwerveModuleConfiguration.backLeftConfig());
        m_backRightModule = new SwerveModule(SwerveModuleConfiguration.backRightConfig());
        m_PosEstimator = new SwerveDrivePoseEstimator(m_kinematics, 
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

    public Pose2d getEstimatedPosition(){
        return m_PosEstimator.getEstimatedPosition();
    }

    public double distanceToBestTargetInInches(){
        var targetO = cameraWrapper.getBestTarget();
        if (targetO.isPresent()){
            var target = targetO.get();
            double targetHeight = cameraWrapper.getAprilTagPos(target.getFiducialId())
                                .map(pos -> pos.getZ())
                                .orElse(0.462788);
            return Units.metersToInches(PhotonUtils.calculateDistanceToTargetMeters(
                Constants.CAMERA_HEIGHT_METERS, 
                targetHeight, Constants.CAMERA_PITCH_RADIANS, Units.degreesToRadians(target.getPitch())));
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public void periodic() {
   /*      m_PosEstimator.update(getGyroscopeRotation(), getSwerveModulePositions());
         cameraWrapper.getRobotPoseFromCamera(m_PosEstimator.getEstimatedPosition()).ifPresent(cameraEstimate -> {
            m_PosEstimator.addVisionMeasurement(cameraEstimate.estimatedPose.toPose2d(), cameraWrapper.getLatestResultTimestamp());
            postRobotPositionFromCamera(cameraEstimate.estimatedPose, distanceToBestTargetInInches());
        });*/
        putRads();
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

    private void postRobotPositionFromCamera(Pose3d pos3d, double distanceToTarget) {
        var pos2d = pos3d.toPose2d();
        SmartDashboard.putNumber("Robot - X", pos2d.getTranslation().getX());
        SmartDashboard.putNumber("Robot - Y",  pos2d.getTranslation().getY());
        SmartDashboard.putNumber("Rotation", Math.toDegrees(pos2d.getRotation().getDegrees()));
        SmartDashboard.putNumber("To Tag Inches", distanceToTarget);
        var aprilTagPos0 = getAprilPos();
        if (aprilTagPos0.isPresent()) {
            var pos=aprilTagPos0.get();
            SmartDashboard.putString("April Tag Pos: ","X: "+pos.getX()+"Y: "+pos.getY()+"Z: "+pos.getZ());
        }
        else {
            SmartDashboard.putString("April Tag Pos: ","Not Found");
        }

    }

    public void putRads() {
        SmartDashboard.putNumber("Radish-FL",m_frontLeftModule.getAbsoluteAngle());
        SmartDashboard.putNumber("Radish-FR",m_frontRightModule.getAbsoluteAngle());//Oh no FR=french
        SmartDashboard.putNumber("Radish-BL",m_backLeftModule.getAbsoluteAngle());
        SmartDashboard.putNumber("Radish-BR",m_backRightModule.getAbsoluteAngle());
    }

    public Optional<Pose3d> getAprilPos() {
        var targetO = cameraWrapper.getBestTarget();
        if (targetO.isPresent()){
            var target = targetO.get();
            return cameraWrapper.getAprilTagPos(target.getFiducialId());
        }
        return Optional.empty();
    }
}