package frc.robot.utils;

import java.util.Optional;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonTrackedTarget;

public class KnightsCameraUtils {
    private final PhotonCamera camera;
    private final int fiducialId;
    private Optional<PhotonTrackedTarget> nullResult;
    public double currentResultTS;

    public KnightsCameraUtils(PhotonCamera camera, int fiducialId){
        this.camera = camera;
        this.nullResult = Optional.ofNullable(null);
        this.currentResultTS = 0;
        this.fiducialId = fiducialId;
    }
    public Optional<PhotonTrackedTarget> getBestTargetForFiducialId(){
        var latestResult = camera.getLatestResult();
        if (latestResult.hasTargets()) {
            Optional<PhotonTrackedTarget> target = latestResult.targets.stream()
            .filter(t -> t.getFiducialId() == fiducialId)
            .filter(t -> t.getPoseAmbiguity() <= 0.2)
            .findFirst();
        if (target.isPresent()){
            this.currentResultTS = latestResult.getTimestampSeconds();
            return target;
        }
        }
       // return latestResult.getTimestampSeconds() - currentResultTS <= 200 ? currentTarget : nullResult;
       return nullResult;
    }
}
