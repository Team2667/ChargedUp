package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static frc.robot.Constants.GamePieceType;;

public class Intake extends SubsystemBase {
	private CANSparkMax intakeMotor;
	private GamePieceType currentGamePieceType = GamePieceType.Cube;


	public Intake() {
		intakeMotor = new CANSparkMax(Constants.IntakeId, MotorType.kBrushless);
	}

	public void set(double speed){
		double actualSpeed = currentGamePieceType == GamePieceType.Cone ? -1 * speed : speed;
		intakeMotor.set(actualSpeed);
	}

	public void stop() {
		intakeMotor.stopMotor();
	}

	public void setGamePieceType(GamePieceType type){
        this.currentGamePieceType = type;
    }

	@Override
	public void periodic(){
		// Output position information to smart dashboard.

	}
}
