import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase {
    private CANSparkMax wristMotor;
    private AbsoluteEncoder encoder;

    public Wrist(){
        wristMotor = new CANSparkMax(Constants.wristId, MotorType.kBrushless);
        encoder = wristMotor.getAbsoluteEncoder(SparkMaxAbsoluteEncoder.Type.kDutyCycle);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Wrist Position", encoder.getPosition());
    }
    
}