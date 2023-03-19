package frc.robot.commands;

import org.w3c.dom.css.ElementCSSInlineStyle;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Elevator;

public class MoveElevatorCommand extends CommandBase{
    XboxController controller;
    Elevator elevator;
    
    public MoveElevatorCommand(Elevator elevator, XboxController controller){
        addRequirements(elevator);
        this.elevator=elevator;
        this.controller = controller;
    }

    @Override
    public void execute(){
        int dPadDir=controller.getPOV();
        SmartDashboard.putNumber("Poopa Stinka", dPadDir);

       if(dPadDir==-1)
       {
           elevator.stop();
            return;
        }
        switch(dPadDir)
        {
            case 0:
                elevator.set(0.2);
            break;
            case 90:
            break;
            case 180:           
                elevator.set(-0.2);//Ingsoc got us... it was supposed to be guillotine :(
            break;
            case 270:
            break;
            default:
                elevator.stop();
        }
        // Raize and lower the guillotine in response to dpad buttons:
        // https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/commands/ExtendCommand.java#L24
    }
}
