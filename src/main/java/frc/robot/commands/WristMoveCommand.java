package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class WristMoveCommand extends CommandBase{
	Wrist wrist;
	XboxController controller;
	public WristMoveCommand(Wrist wrist,XboxController controller)
	{
			addRequirements(wrist);
			this.wrist=wrist;
			this.controller=controller;
	}
	public void execute()
	{
		int dPadDir=controller.getPOV();

       if(dPadDir==-1)
       {
           wrist.stop();
            return;
        }
        switch(dPadDir)
        {
            case 0:
            break;
            case 90:
			wrist.set(0.2);
            break;
            case 180:           
            break;
            case 270:
			wrist.set(-0.2);//Ingsoc got us... it was supposed to be guillotine :(
            break;
            default:
                wrist.stop();
        }
        // Raize and lower the guillotine in response to dpad buttons:
        // https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/commands/ExtendCommand.java#L24
    }
	
	
}
