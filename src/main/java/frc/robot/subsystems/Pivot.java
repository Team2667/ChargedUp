package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// See https://github.com/Team2667/RapidReact2022/blob/master/src/main/java/frc/robot/subsystems/Arms.java
// As a guide
// 
public class Pivot  extends SubsystemBase{
    private CANSparkMax extenderMotor;
    private RelativeEncoder extenderEncoder;
    private SparkMaxPIDController sparkPidController;

    private double pV = 0.08;
    private double iV = 0;
    private double dV = 0;

    public Pivot() {
        // Initialize extenderMotor
        // Initialize sparkPidController
        // May need to invert the motor
        extenderMotor=new CANSparkMax(Constants.extenderMotor,MotorType.kBrushless);
        extenderEncoder=extenderMotor.getEncoder();
        sparkPidController=extenderMotor.getPIDController();
        updatePidVals();

    }

    private void updatePidVals()
    {
        sparkPidController.setP(pV);
        sparkPidController.setI(iV);
        sparkPidController.setD(dV);
        // set the pid values in the controller
    }

    public void setPosition(double numRevs){
        updatePidVals();
        sparkPidController.setReference(numRevs, CANSparkMax.ControlType.kPosition);
    }

    public void stop() {
        // code to stop the motor
        extenderMotor.stopMotor();
    }
    public void set(double speed)
    {
        extenderMotor.set(speed);
    }
    @Override
    public void periodic()
    {

        SmartDashboard.putNumber("Pivot Pos",extenderEncoder.getPosition());
        double sp=SmartDashboard.getNumber("Pivot P", pV);
        double si=SmartDashboard.getNumber("Pivot I", iV);
        double sd=SmartDashboard.getNumber("Pivot D", dV);
        if(sp!=pV || si!=pV || sd!=dV)
        {
            pV=sp;
            iV=si;
            dV=sd;
            updatePidVals();
        }
        SmartDashboard.putNumber("Pivot P", pV);
        SmartDashboard.putNumber("Pivot I", iV);
        SmartDashboard.putNumber("Pivot D", dV);


        
    }
}
