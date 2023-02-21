package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Pinchy extends SubsystemBase {
    public CANSparkMax leftyPinchyMotor;
    public CANSparkMax rightyPinchyMotor;

    public boolean CurrentState=false;

    public Pinchy() {
        leftyPinchyMotor=new CANSparkMax(Constants.LEFT_PINCHY_ID,MotorType.kBrushless);
        leftyPinchyMotor.setInverted(true);
        rightyPinchyMotor=new CANSparkMax(Constants.RIGHT_PINCHY_ID,MotorType.kBrushless);

    }

    public void setPinchySpeed(double speed) {
        leftyPinchyMotor.set(speed);
        rightyPinchyMotor.set(speed);
    }

    public void stopPinchy() {
        leftyPinchyMotor.stopMotor();
        rightyPinchyMotor.stopMotor();
    }

    public void forward(double speed) {
        setPinchySpeed(speed);
    }

    public void backward(double speed) {
        setPinchySpeed(speed);
    }

    public void off() {
        stopPinchy();
    }

    public void togglePinchy() {
        CurrentState=!CurrentState;
        if(CurrentState) {
            setPinchySpeed(Constants.PINCHY_PERCENTAGE/100);
        }
        else {
            stopPinchy();
        }
    }
}
