package frc.robot.commands;

import javax.swing.event.TableColumnModelListener;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class RotateRobot extends CommandBase{
    private static final TrapezoidProfile.Constraints OMEGA_CONSTRAINTS = new TrapezoidProfile.Constraints(0.5,0.5);
    private final ProfiledPIDController omegaController = new ProfiledPIDController(0.2,0,0,OMEGA_CONSTRAINTS);
    private double fieldRealtiveAngle;
    private double turn;
    private final DriveTrain driveTrain;

    public RotateRobot(DriveTrain driveTrain, double fieldRelativeAngleInRadians){
        this.fieldRealtiveAngle = fieldRelativeAngleInRadians;
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override
    public void initialize(){
        omegaController.setTolerance(25);
        omegaController.enableContinuousInput(-Math.PI, Math.PI);
    }

    @Override
    public void execute() {

        var turn = 0.0; // Use omega controller to calculate the speed of rotation. Need to provide the goal angle and
                        // the current gyrosocpeRotation of the drive train.
        turn=omegaController.calculate(driveTrain.getGyroscopeRotation().getRadians(),fieldRealtiveAngle);
        var speeds = ChassisSpeeds.fromFieldRelativeSpeeds(0,0,turn,driveTrain.getGyroscopeRotation());

        driveTrain.drive(speeds);
    }

    @Override 
    public boolean isFinished(){
        if(omegaController.atGoal())System.out.println("rotaterobot finished");
        return omegaController.atGoal(); // use omegaController to see if we are at the goal position

    }
}