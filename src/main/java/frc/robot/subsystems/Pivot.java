package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// See https://github.com/Team2667/RapidReact2022/blob/master/src/main/java/frc/robot/subsystems/Arms.java
// As a guide
// 
public class Pivot  extends SubsystemBase{
    private CANSparkMax extenderMotor;
    private SparkMaxPIDController sparkPidController;
    private double pV = 0.08;
    private double iV = 0;
    private double dV = 0;

    public Pivot() {
        // Initialize extenderMotor
        // Initialize sparkPidController
        // May need to invert the motor
    }

    private void updatePidVals()
    {
        // set the pid values in the controller
    }

    private void setPosition(int numRevs){
        //sparkPidController.setReference
        // update pid values
        // setReference
    }

    private void stop() {
        // code to stop the motor
    }
    
}
