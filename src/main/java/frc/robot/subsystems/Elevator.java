package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Elevator extends SubsystemBase {
	private CANSparkMax elevatorLeft, elevatorRight;
	private int LeftID, RightID;
	private SparkMaxPIDController sparkPidController;
	private RelativeEncoder encoder;
	private double pV = 0.04;
    private double iV = .00005;
    private double dV = 0;


	public Elevator() {
		LeftID=Constants.ElevatorLeftID;
		RightID=Constants.ElevatorRightID;
		elevatorLeft= new CANSparkMax(LeftID, MotorType.kBrushless);
		elevatorRight= new CANSparkMax(RightID, MotorType.kBrushless);
		elevatorLeft.follow(elevatorRight);
		sparkPidController = elevatorRight.getPIDController();
		encoder = elevatorRight.getEncoder();
		updatePidVals();
	}

	public void setElevatorPosition(double position){
		// TODO Set the elevator position like 
		// https://github.com/Team2667/ChargedUp/blob/handle_cones_cubes/src/main/java/frc/robot/subsystems/Pivot.java#L56
	}

	public boolean isAtSetPoint(double rotations) {
		// TODO: Check to see if encoder.getPosition() is within a specified number of rotations from value being passed in.
		return false;
    }

	public void stop() {
		// stop the motors
	}

	public void set(double speed){
		// TODO set elevator left to the specified value
	}


	private void updatePidVals()
    {
        sparkPidController.setP(pV);
        sparkPidController.setI(iV);
        sparkPidController.setD(dV);
        // set the pid values in the controller
    }

	@Override
	public void periodic(){
		// Output position information to smart dashboard.
	}
}
