package frc.robot.commands;

import java.util.function.Supplier;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.KnightsCameraUtils;

/*
// NOT READY TO USE
 An example class for tracking a target that is not ready for use but included as example code to refer to.
 The code was obtained from the following youtube video https://www.youtube.com/watch?v=TG9KAa2EGzQ&t=1807s 
 This example relies on a field estimator which
 we need to figure out how to use or find an alternative.

*/
public class TrackTarget extends CommandBase {
    private DriveTrain driveTrain;

    private static final TrapezoidProfile.Constraints X_CONSTRAINTS = new TrapezoidProfile.Constraints(3, 2);
    private static final TrapezoidProfile.Constraints Y_CONSTRAINTS = new TrapezoidProfile.Constraints(3,2);
    private static final TrapezoidProfile.Constraints OMEGA_CONSTRAINTS = new TrapezoidProfile.Constraints(8, 8);
    private final ProfiledPIDController xController = new ProfiledPIDController(3,0,0,X_CONSTRAINTS);
    private final ProfiledPIDController yController = new ProfiledPIDController(3,0,0,Y_CONSTRAINTS);
    private final ProfiledPIDController omegaController = new ProfiledPIDController(2,0,0,OMEGA_CONSTRAINTS);

    private static final Transform3d TAG_TO_GOAL = new Transform3d(
        new Translation3d(1.5, 0.0,0.0),
        new Rotation3d(0.0, 0.0, Math.PI)
    );

    private static final Transform3d ROBOT_TO_CAMERA = new Transform3d(
        new Translation3d(.25, 0.0,0.0),
        new Rotation3d(0.0, 0.0, 0.0)
    );
    //private final ProfiledPIDController xController = new ProfiledPidController(3,)

    public TrackTarget(DriveTrain driveTrain, Pose2d goal){
        addRequirements(driveTrain);
        this.driveTrain  = driveTrain;
        xController.setTolerance(.2);
        xController.enableContinuousInput(.25, .25);
        xController.setGoal(goal.getX());
        yController.setTolerance(.2);
        yController.enableContinuousInput(.25, .25);
        yController.setGoal(goal.getY());
        omegaController.setTolerance(Units.degreesToRadians(3));
        omegaController.enableContinuousInput(-Math.PI, Math.PI);
        omegaController.setGoal(goal.getRotation().getRadians());
    }

    @Override
    public void execute() {
        var robotPos2d = driveTrain.getEstimatedPosition();
        var ySpeed = yController.atGoal() ? 0.0 : yController.calculate(robotPos2d.getX());
        var xSpeed = xController.atGoal() ? 0.0 : xController.calculate(robotPos2d.getX());
        var omegaSpeed = omegaController.atGoal() ? 0.0 : omegaController.calculate(robotPos2d.getRotation().getRadians());
            
        driveTrain.drive(ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, omegaSpeed, robotPos2d.getRotation()));
    }

    @Override
    public boolean isFinished() {
        return (yController.atGoal() && xController.atGoal() && omegaController.atGoal());
    }

    @Override
    public void end(boolean isInterrupted) {
        driveTrain.stop();
    }
}