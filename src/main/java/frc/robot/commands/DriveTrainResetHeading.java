package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveTrainResetHeading extends CommandBase{
    private DriveTrain driveTrain;

    public DriveTrainResetHeading(DriveTrain driveTrain){
        this.driveTrain = driveTrain;
        addRequirements(driveTrain);
    }

    @Override()
    public void initialize(){
        driveTrain.setRotationalOffsetToCurrent();
    }

    @Override()
    public boolean isFinished(){
        return true;
    }
}
