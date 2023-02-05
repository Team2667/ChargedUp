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

public class GetInRangeOfTarget extends CommandBase {
    private PhotonCamera photonCamera;
    private DriveTrain driveTrain;
    private PIDController controller = new PIDController(0.1, 0, 0.0);
    private List<Integer> fiducialIds  = Arrays.asList(1,2,3,6,7,8);
    private double goalRange = Units.feetToMeters(2);

    public GetInRangeOfTarget(DriveTrain driveTrain, PhotonCamera camera){
        this.driveTrain = driveTrain;
        this.photonCamera = camera;
        controller.setTolerance(.2);
        controller.enableContinuousInput(-1, 1);
    }

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
            var forwardSpeed = controller.calculate(range, goalRange);
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
