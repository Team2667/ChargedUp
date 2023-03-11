package frc.robot.utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.PhotonPoseEstimator.PoseStrategy;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.math.geometry.Translation3d;

public class PhotonCameraWrapper {
    private PhotonCamera camera;
    private double lastResultTimeStamp;
    private AprilTagFieldLayout aprilTagLayout;
    private Transform3d cameraToRobot;
    private PhotonPoseEstimator photonPoseEstimator;

    public PhotonCameraWrapper(PhotonCamera camera) {
        this.camera = camera;
        cameraToRobot = new Transform3d(
                        new Translation3d(0.5, 0.0, 0.5),
                        new Rotation3d(
                                0, 0,
                                0));
        photonPoseEstimator = new PhotonPoseEstimator(getLayout(), PoseStrategy.LOWEST_AMBIGUITY, camera, cameraToRobot);
    }

    public Optional<PhotonTrackedTarget> getBestTargetForFiducialId(List<Integer> tagIds){
        var latestResult = camera.getLatestResult();
        if (latestResult.hasTargets()) {
            Optional<PhotonTrackedTarget> target = latestResult.targets.stream()
            .filter(t -> tagIds.contains(t.getFiducialId()))
            .filter(t -> t.getPoseAmbiguity() <= 0.2)
            .findFirst();
            return target;
        }
       return Optional.ofNullable(null);
    }

    public Optional<PhotonTrackedTarget> getBestTarget() {
        var latestResult = camera.getLatestResult();
        if (latestResult.hasTargets()){
            return Optional.of(latestResult.getBestTarget());
        }
        return Optional.ofNullable(null);
    }

    public Optional<EstimatedRobotPose> getRobotPoseFromCamera(Pose2d prevEstimatedRobotPose){
        photonPoseEstimator.setReferencePose(prevEstimatedRobotPose);
        return photonPoseEstimator.update();
    }

    public double getLatestResultTimestamp() {
        return lastResultTimeStamp;
    }

    public Optional<Pose3d> getAprilTagPos(int fiducialId) {
        return getLayout().getTagPose(fiducialId);
    }

    private AprilTagFieldLayout getLayout(){
        if (aprilTagLayout == null) {
            aprilTagLayout = getFieldLayout();
        }
        return aprilTagLayout;
    }

    // Put tags 14 ¼ inches from carpet to bottom of plate (manual page 41)
    // camera is 18 3/8 inches from the ground
    public static AprilTagFieldLayout getFieldLayout() {
        try {
            return AprilTagFields.k2023ChargedUp.loadAprilTagLayoutField();
        } catch(IOException exception){
            return null;
        }
    }
}
