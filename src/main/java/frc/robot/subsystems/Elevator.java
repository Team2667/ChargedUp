package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Elevator extends SubsystemBase {
	private CANSparkMax leftMotor, rightMotor;
	private SparkMaxPIDController sparkPidController;
	private RelativeEncoder encoder;
	private double pV = 4e-2;
	private double iV = 0;
    private double dV = 0;


	public Elevator() {
		leftMotor= new CANSparkMax(Constants.ElevatorLeftID, MotorType.kBrushless);
		rightMotor= new CANSparkMax(Constants.ElevatorRightID, MotorType.kBrushless);
		leftMotor.follow(rightMotor);
		leftMotor.setInverted(true);
		sparkPidController = rightMotor.getPIDController();
		encoder = rightMotor.getEncoder();
		//updatePidVals();
	}

	public void setElevatorPosition(double position){
		// TODO Set the elevator position like 
		// https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/subsystems/Pivot.java#L56
	}

	public boolean isAtSetPoint(double rotations) {
		// TODO: Check to see if encoder.getPosition() is within a specified number of rotations from value being passed in.
		return false;
    }

	public boolean reverseLimitSwitchPressed(){//Limpet
		return rightMotor.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
	}

	public boolean forwardLimitSwitchPressed(){
		//TODO: Implement
		return false;
	}

	public void stop() {
		// stop the motors
		rightMotor.stopMotor();
	}

	public void set(double speed){
		SmartDashboard.putNumber("elevator speed", speed);
		rightMotor.set(speed);
	}

	@Override
	public void periodic(){
		// Output position information to smart dashboard.
	}

	private void updatePidVals()
    {
        sparkPidController.setP(pV);
        sparkPidController.setI(iV);
        sparkPidController.setD(dV);
        // set the pid values in the controller
    }
}
