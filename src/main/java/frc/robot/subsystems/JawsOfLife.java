package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;

public class JawsOfLife extends SubsystemBase {
    Solenoid solenoid;

    public JawsOfLife() {
        // Constants.PCM_CAN_ID
        // solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1,2);
        // solenoid.set(kReverse);
        solenoid = new Solenoid(1, PneumaticsModuleType.REVPH, 0);
    }

    public void toggle() {
        solenoid.toggle();
    }

}