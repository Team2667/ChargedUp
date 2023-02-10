package frc.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.PhotonCameraWrapper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetInRangeOfTarget extends CommandBase {
    private PhotonCamera photonCamera;
    private DriveTrain driveTrain;
    private PIDController controller = new PIDController(0.3, 0, 0.0);
    private List<Integer> fiducialIds  = Arrays.asList(1,2,3,6,7,8);
    private double goalRange = Units.feetToMeters(2);

    public GetInRangeOfTarget(DriveTrain driveTrain, PhotonCamera camera){
        this.driveTrain = driveTrain;
        this.photonCamera = camera;
        controller.setTolerance(1);
        controller.enableContinuousInput(-1, 1);
    }
/*
 * 5 ft start dist
 * trial 1 18.5 inches
 * trial 2 24 inches
 * trial 3 18 inches
 */
    @Override
    public void execute() {
        var targetO = PhotonCameraWrapper.getBestTargetForFiducialId(photonCamera,fiducialIds);
        if (targetO.isPresent()){

            var target = targetO.get();
            var targetpos = PhotonCameraWrapper.getFieldLayout().getTagPose(target.getFiducialId());
            var targetHeight = targetpos.map(p -> p.getZ()).orElse(0.462788);
            double range =
                PhotonUtils.calculateDistanceToTargetMeters(
                    Constants.CAMERA_HEIGHT_METERS,
                    targetHeight,
                    Constants.CAMERA_PITCH_RADIANS,
                    Units.degreesToRadians(target.getPitch()));
            SmartDashboard.putNumber("MarchMay-range",range);
            SmartDashboard.putNumber("MarchMay-goalRange",goalRange);
            var forwardSpeed = controller.calculate(range, goalRange);
            SmartDashboard.putNumber("MarchMay-sneed",forwardSpeed);
            driveTrain.moveFieldRelative(forwardSpeed, 0);
        } else {
            driveTrain.stop();
        }
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }
}
