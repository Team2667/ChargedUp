package frc.robot.utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Pose3d;

public class PhotonCameraWrapper {
    private PhotonCamera camera;
    private double lastResultTimeStamp;
    private AprilTagFieldLayout aprilTagLayout;

    public PhotonCameraWrapper(PhotonCamera camera) {
        this.camera = camera;
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
    
    public Optional<Pose3d> getRobotPosFromCamera(){
        var latestResults = camera.getLatestResult();
        if (latestResults.hasTargets()){
            lastResultTimeStamp = latestResults.getTimestampSeconds();
            var target = latestResults.getBestTarget();
            var targetPos = getFieldLayout().getTagPose(target.getFiducialId()).get();
            var camToTargetTrans = target.getBestCameraToTarget();
            var camPos = targetPos.transformBy(camToTargetTrans.inverse());
            return Optional.of(camPos);
        }
        return Optional.ofNullable(null);
    }

    public double getLatestResultTimestamp() {
        return lastResultTimeStamp;
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
