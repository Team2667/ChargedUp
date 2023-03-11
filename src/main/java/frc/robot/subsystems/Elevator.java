package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Elevator extends SubsystemBase {
	CANSparkMax GuillotineLeft, GuillotineRight;
	int LeftID, RightID;
	public Elevator() {
		LeftID=Constants.ElevatorLeftID;//Guillotine Left
		RightID=Constants.ElevatorRightID;//Guillotine Right
		GuillotineLeft= new CANSparkMax(LeftID, MotorType.kBrushless);
		GuillotineRight= new CANSparkMax(RightID, MotorType.kBrushless);
		GuillotineLeft.follow(GuillotineRight);
	}
}
