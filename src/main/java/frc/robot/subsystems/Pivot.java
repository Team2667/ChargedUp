package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import frc.robot.Constants;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.SparkMaxLimitSwitch;

import java.lang.Math;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.CalibratePivot;
// See https://github.com/Team2667/RapidReact2022/blob/master/src/main/java/frc/robot/subsystems/Arms.java
// As a guide
public class Pivot  extends SubsystemBase{
    private CANSparkMax rotatorMotor;
    private RelativeEncoder extenderEncoder;
    private SparkMaxPIDController sparkPidController;
    public SparkMaxLimitSwitch ReverseLimitSwitch;
    public CalibratePivot Calibpivot;
    private double pV = 0.04;
    private double iV = .00005;
    private double dV = 0;

    public Pivot() {
        // Initialize rotatorMotor
        // Initialize sparkPidController
        // May need to invert the motor
        rotatorMotor=new CANSparkMax(Constants.rotatorMotor,MotorType.kBrushless);
        extenderEncoder=rotatorMotor.getEncoder();
        sparkPidController=rotatorMotor.getPIDController();
        updatePidVals();
        rotatorMotor.setInverted(false);
        ReverseLimitSwitch=rotatorMotor.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
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
        sparkPidController.setIAccum(0);
        sparkPidController.setReference(numRevs, CANSparkMax.ControlType.kPosition);
    }

    public boolean isAtSetPoint(double rotations) {
        return (Math.abs(extenderEncoder.getPosition()-rotations) < Constants.ROTATIONAL_LENIENCY);
    }

    public double getPos() {
        return extenderEncoder.getPosition();
    }

    public void stop() {
        // code to stop the motor
        rotatorMotor.stopMotor();
    }
    public void set(double speed)
    {
        rotatorMotor.set(speed);
    }
    public boolean getLitch() {
        return ReverseLimitSwitch.isPressed();
    }
    @Override
    public void periodic()
    {

        SmartDashboard.putNumber("Pivot Pos",extenderEncoder.getPosition());
        double sp=SmartDashboard.getNumber("Pivot P", pV);
        double si=SmartDashboard.getNumber("Pivot I", iV);
        double sd=SmartDashboard.getNumber("Pivot D", dV);
        if(sp!=pV||si!=pV||sd!=dV)
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
