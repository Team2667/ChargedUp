package frc.robot.commands;

import java.util.Arrays;
import java.util.List;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GetInRangeOfTarget extends CommandBase {
    private DriveTrain driveTrain;
    private PIDController controller = new PIDController(1, 0, 0.0);
    private double goalRange = Units.feetToMeters(2);

    public GetInRangeOfTarget(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        controller.setTolerance(.1);
        controller.enableContinuousInput(-.5, .5);
    }

    @Override
    public void execute() {
        double range = driveTrain.distanceToBestTargetInInches();
        var forwardSpeed = controller.calculate(range, goalRange);
        driveTrain.moveFieldRelative(forwardSpeed, 0);
    }

    @Override
    public boolean isFinished() {
        return controller.atSetpoint();
    }
}
