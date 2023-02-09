package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.JawsOfLife;

public class JawsCommand extends CommandBase {
    private JawsOfLife jaws;

    public JawsCommand(JawsOfLife jawsOfLife) {
        jaws = jawsOfLife;
    }

    public void execute() {
        jaws.toggle();
    }

    // Return true immediately as to not run again
    public boolean isFinished() {
        return true;
    }

}
