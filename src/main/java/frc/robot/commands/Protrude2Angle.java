package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtender;
public class Protrude2Angle extends CommandBase {
    ArmExtender extender;
    double rotations;

    public Protrude2Angle(ArmExtender extender, double rotations) {
        addRequirements(extender);
        this.extender=extender;
        this.rotations=rotations;
    }

    @Override
    public void initialize() {
        extender.setPosition(rotations);
    }
    
    @Override
    public boolean isFinished() {
        return extender.isAtSetPoint(rotations);
    }
}
