package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxPIDController;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// See https://github.com/Team2667/RapidReact2022/blob/master/src/main/java/frc/robot/subsystems/Arms.java
// As a guide
// 
public class ArmExtender  extends SubsystemBase{
    private CANSparkMax extenderMotor;
    private SparkMaxPIDController sparkPidController;
    private RelativeEncoder extenderEncoder;
    private double pV = 0.08;
    private double iV = 0;
    private double dV = 0;
    private SparkMaxPIDController GoGoGadgetPID;

    public ArmExtender() { //R.I.P. Go Go Gadget
        extenderMotor=new CANSparkMax(Constants.ARM_CANID,MotorType.kBrushless);
        extenderEncoder=extenderMotor.getEncoder();
        sparkPidController=extenderMotor.getPIDController();
        
        // Initialize sparkPidController
        // May need to invert the motor
    }
    @Override
    public void periodic()
    {

        SmartDashboard.putNumber("Inspector Gadget Pos",extenderEncoder.getPosition());
        double sp=SmartDashboard.getNumber("Gadget P", pV);
        double si=SmartDashboard.getNumber("Gadget I", iV);
        double sd=SmartDashboard.getNumber("Gadget D", dV);

        
        if(sp!=pV || si!=pV || sd!=dV)
        {
            pV=sp;
            iV=si;
            dV=sd;
            updatePidVals();
        }
        SmartDashboard.putNumber("Gadget P", pV);
        SmartDashboard.putNumber("Gadget I", iV);
        SmartDashboard.putNumber("Gadget D", dV);


        
    }

    public void set(double speed) {
        extenderMotor.set(speed);
    }

    private void updatePidVals() {
        // set the pid values in the controller
    }

    private void setPosition(int numRevs) {
        //sparkPidController.setReference
        // update pid values
        // setReference
    }

    public void stop() {
        extenderMotor.stopMotor();
    }
    
}
