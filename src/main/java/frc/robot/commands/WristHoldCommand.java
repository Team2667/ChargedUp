package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class WristHoldCommand extends CommandBase{
    private Wrist wrist;

    public WristHoldCommand(Wrist wrist){
        this.wrist = wrist;
        addRequirements(wrist);
        wrist.setDefaultCommand(this);    
    }

    @Override
    public void execute(){
        var pos = wrist.getPosition();
        wrist.setPosition(pos);
    }
}
