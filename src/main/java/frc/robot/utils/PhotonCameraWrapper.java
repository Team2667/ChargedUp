package frc.robot.utils;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;

public class PhotonCameraWrapper {
    public static Optional<PhotonTrackedTarget> getBestTargetForFiducialId(PhotonCamera camera, List<Integer> tagIds){
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
