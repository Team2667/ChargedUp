package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.GamePieceType;

public class Wrist extends SubsystemBase {
    private CANSparkMax wristMotor;
    private SparkMaxPIDController controller;
    private RelativeEncoder encoder;
    private GamePieceType currentGamePieceType = GamePieceType.Cube;
    private double pV = 4e-2;
	private double iV = 0;
    private double dV = 0;

    public Wrist(){
        wristMotor = new CANSparkMax(Constants.wristId, MotorType.kBrushless);
        controller = wristMotor.getPIDController();
        setPid();

        encoder = wristMotor.getEncoder();
    }

    public void set(double speed){
        wristMotor.set(speed);
    }

    public void stop()
    {
        wristMotor.stopMotor();
    }

    public boolean forwardLimitSwitchPressed(){
        return wristMotor.getForwardLimitSwitch(Type.kNormallyOpen).isPressed();
    }

    public void setPosition(double pos){
        controller.setReference(pos, ControlType.kPosition);
    }

    public double getPosition(){
        return encoder.getPosition();
    }

    public boolean reversedLimitSwitchPressed(){
        return wristMotor.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
    }

    public void setGamePieceType(GamePieceType type){
        this.currentGamePieceType = type;
    }

    @Override
    public void periodic(){
 
    }

    private void setPid(){
        controller.setP(pV);
        controller.setI(iV);
        controller.setD(dV);
    }
    
}