package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Intake extends SubsystemBase {
	private CANSparkMax intakeMotor;

	public Intake() {
		intakeMotor = new CANSparkMax(Constants.IntakeId, MotorType.kBrushless);
	}

	public void set(double speed){
		// TODO set elevator left to the specified value
	}

	public void stop() {
		// stop the motors
	}

	@Override
	public void periodic(){
		// Output position information to smart dashboard.
	}
}
