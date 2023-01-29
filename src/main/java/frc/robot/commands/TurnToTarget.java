package frc.robot.commands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.KnightsCameraUtils;

public class TurnToTarget extends CommandBase{
    private static final TrapezoidProfile.Constraints OMEGA_CONSTRAINTS = new TrapezoidProfile.Constraints(0.4, 0.4);
    private final ProfiledPIDController omegaController = new ProfiledPIDController(2,0,0,OMEGA_CONSTRAINTS);
    private KnightsCameraUtils kCameraUtils;
    private final DriveTrain driveTrain;

    public TurnToTarget(PhotonCamera camera, DriveTrain driveTrain, int fiducialId){
        this.kCameraUtils = new KnightsCameraUtils(camera, fiducialId);
        this.driveTrain = driveTrain;
        omegaController.setTolerance(.2);
    
    }

    @Override
    public void execute() {
        var targetOpt = kCameraUtils.getBestTargetForFiducialId();
        if (targetOpt.isPresent()){
            var target = targetOpt.get();
            var turn = omegaController.calculate(target.getYaw(),0);
            var speeds = ChassisSpeeds.fromFieldRelativeSpeeds(0, 0, turn, driveTrain.getGyroscopeRotation());
            driveTrain.drive(speeds);
        }
    }

    @Override 
    public boolean isFinished(){
        return omegaController.atGoal() || !kCameraUtils.getBestTargetForFiducialId().isPresent();
    }
}
