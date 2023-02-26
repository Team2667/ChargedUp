
package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Pivot;

public class PivotCommand extends CommandBase{
    private Pivot pivot;
    private XboxController joystick;
    private int dPadDir;
    private int prevDir=-1;
    private int usableDpad;
    public PivotCommand(Pivot pivot,XboxController joystick)
    {
        addRequirements(pivot);
        this.pivot=pivot;
        this.joystick=joystick;
        //pivot.setDefaultCommand(this);
    }
    @Override
    public void execute()
    {
        int dPadDir=joystick.getPOV();
        //if(dPadDir==-1 || prevDir==-1)
        //    return;
        //    prevDir=dPadDir;
        usableDpad=dPadDir;
        if(dPadDir==-1)
        {
            pivot.stop();
            return;
        }
        SmartDashboard.putNumber(getName(), usableDpad);
        switch(usableDpad)
        {
            case 0:
            pivot.set(0.25);
            break;
            case 90:
            break;
            case 180:
            pivot.set(-0.25);
            break;
            case 270:
            //nothing
            default:

        }

    }
   
    @Override
    public void end(boolean isinterrupted)
    {
        pivot.stop();
    }
    
}
