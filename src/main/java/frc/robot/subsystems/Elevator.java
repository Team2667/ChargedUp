package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxLimitSwitch.Type;
import static frc.robot.Constants.GamePieceType;
import static frc.robot.Constants.GoalPosition;

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
	GamePieceType currentGamePieceType = GamePieceType.Cube;


	public Elevator() {
		leftMotor= new CANSparkMax(Constants.ElevatorLeftID, MotorType.kBrushless);
		rightMotor= new CANSparkMax(Constants.ElevatorRightID, MotorType.kBrushless);
		leftMotor.follow(rightMotor);
		//leftMotor.setInverted(true);
		sparkPidController = rightMotor.getPIDController();
		encoder = rightMotor.getEncoder();

		updatePidVals();
	}

	public void setElevatorPosition(GoalPosition position){
		double pos = currentGamePieceType == GamePieceType.Cone ? getPositionForCones(position) :
										getPositionForCubes(position);
		sparkPidController.setReference(pos, ControlType.kPosition);
	}

	public boolean isAtSetPoint(GoalPosition goalPos){
		double pos = currentGamePieceType == GamePieceType.Cone ? getPositionForCones(goalPos) :
						getPositionForCubes(goalPos);
		return Math.abs(pos - encoder.getPosition()) < Constants.ELEVATOR_MOE;
	}

	public void zeroElevator(){
		encoder.setPosition(0);
	}

	public boolean reverseLimitSwitchPressed(){//Limpet
		return rightMotor.getReverseLimitSwitch(Type.kNormallyOpen).isPressed();
	}

	public boolean forwardLimitSwitchPressed(){
		return rightMotor.getForwardLimitSwitch(Type.kNormallyOpen).isPressed();
	}

	public double getSpeed()
	{
		return rightMotor.get();
	}

	public void setGamePieceType(GamePieceType type) {
		this.currentGamePieceType = type;
	}

	public GamePieceType getCurrentGamePieceType(){
		return currentGamePieceType;
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
		SmartDashboard.putNumber("Elevator Position", encoder.getPosition());
		var gamePiece = currentGamePieceType == GamePieceType.Cone ? "Cones" : "Cubes";
		SmartDashboard.putString("Game Piece Mode",gamePiece);
	}


	private double getPositionForCubes(GoalPosition pos){
		switch(pos){
			case low: return Constants.CUBE_LOW;
			case mid: return Constants.CUBE_MID;
			case high: return Constants.CUBE_HIGH;
			case feeder: return Constants.CUBE_FEEDER;
			case ground: return Constants.CUBE_GROUND;
			default: return Constants.HOME;
		}
	}

	private double getPositionForCones(GoalPosition pos){
		switch(pos){
			case low: return Constants.CONE_LOW;
			case mid: return Constants.CONE_MID;
			case high: return Constants.CONE_HIGH;
			case feeder: return Constants.CONE_FEEDER;
			case ground: return Constants.CONE_GROUND;
			default: return Constants.HOME;
		}
	}

	private void updatePidVals()
    {
        sparkPidController.setP(pV);
        sparkPidController.setI(iV);
        sparkPidController.setD(dV);
    }
}
