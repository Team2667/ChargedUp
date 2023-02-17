package frc.robot.commands;

import javax.swing.plaf.ActionMapUIResource;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ArmExtender;

public class ExtendCommand extends CommandBase{
    private ArmExtender Inspector; //Go Go Gadget Extendo Arm
    private XboxController joystick;
    private int dPadDir;
    private int prevDir=-1;
    private int usableDpad;
    public ExtendCommand(ArmExtender Inspector,XboxController joystick)
    {
        addRequirements(Inspector);
        this.Inspector=Inspector;
        this.joystick=joystick;
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
            Inspector.stop();
            return;
        }
        SmartDashboard.putNumber(getName(), usableDpad);
        switch(usableDpad)
        {
            case 0:
            Inspector.set(-0.1);
            break;
            case 90:
            Inspector.set(0.1);
            break;
            case 180:
            break;
            case 270:
            break;
            default:

        }

    }
   
    @Override
    public void end(boolean isinterrupted)
    {
        Inspector.stop();
    }
    
}
