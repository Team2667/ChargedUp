package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.GamePieceType;

public class Wrist extends SubsystemBase {
    private CANSparkMax wristMotor;
    private RelativeEncoder encoder;
    private GamePieceType currentGamePieceType = GamePieceType.Cube;

    public Wrist(){
        wristMotor = new CANSparkMax(Constants.wristId, MotorType.kBrushless);
        encoder = wristMotor.getAlternateEncoder(8192);
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

    public boolean reversedLimitSwitchPressed(){
        return wristMotor.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
    }

    public void setGamePieceType(GamePieceType type){
        this.currentGamePieceType = type;
    }

    @Override
    public void periodic(){
 
    }
    
}