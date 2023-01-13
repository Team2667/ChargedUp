package frc.robot.subsystems.swerveSupport;

import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoder;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
//import com.revrobotics.CANSparkMax.ControlType;
//Unused import
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveModule {

    private RelativeEncoderResetTracker relativeEncoderTracker = new RelativeEncoderResetTracker();
    private CANCoder absoluteSteerEncoder;
    private CANSparkMax steerMotor;
    private CANSparkMax driveMotor;
    private RelativeEncoder steerRelativeEncoder;
    private SwerveModuleConfiguration cfg;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 4.14528;
    private static final double MAX_VOLTAGE = 12.0;

    public SwerveModule(SwerveModuleConfiguration config) {
        cfg = config;
        absoluteSteerEncoder = createAbsoluteCanEncoder(cfg.steerAbsoluteEncoderCanId, cfg.steeringOffsetInRadians);
        steerMotor = new CANSparkMax(cfg.steerMotorCanId, MotorType.kBrushless);
        setPIDValues(steerMotor, config.steerP, config.steerI, config.steerD);
        driveMotor = new CANSparkMax(cfg.driveMotorCanId, MotorType.kBrushless);
        setPIDValues(driveMotor, cfg.driveP, cfg.driveI, cfg.driveD);
        driveMotor.setInverted(config.driveInverted);
        steerRelativeEncoder = steerMotor.getEncoder();
        steerRelativeEncoder.setPositionConversionFactor(2.0 * Math.PI * cfg.steerReduction);
        steerRelativeEncoder.setVelocityConversionFactor(2.0 * Math.PI * cfg.steerReduction / 60.0);
        resetSteerRelativeEncoder();

        RelativeEncoder driveEncoder = driveMotor.getEncoder();
        double positionConversionFactor = Math.PI * cfg.wheelDiameter * cfg.driveReduction;
        driveEncoder.setPositionConversionFactor(positionConversionFactor);
        driveEncoder.setVelocityConversionFactor(positionConversionFactor / 60.0);
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        // Optimize the reference state to avoid spinning further than 90 degrees
        if (relativeEncoderTracker.isTimeToResetRelativeEncoder(steerRelativeEncoder.getVelocity())){
            resetSteerRelativeEncoder();
        }

        SwerveModuleState state = SwerveModuleState.optimize(desiredState, new Rotation2d(getAbsoluteAngle()));
        setDriveVelocity(state.speedMetersPerSecond);
        setReferenceAngle(state.angle.getRadians());
    }

    public void stop(){
        setDriveVelocity(0.0);
    }
      
    public double getAbsoluteAngle() {
        double angle = Math.toRadians(absoluteSteerEncoder.getAbsolutePosition());
        angle %= 2.0 * Math.PI;
        if (angle < 0.0) {
            angle += 2.0 * Math.PI;
        }
        return angle;
    }

    public void outputSteerAnglesToDashboard(){
        SmartDashboard.putNumber(getSteerLogLabel("Relative Encoder"), steerMotor.getEncoder().getPosition());
        SmartDashboard.putNumber(getSteerLogLabel("Absolute Encoder"), getAbsoluteAngle());
    }

    private void setPIDValues(CANSparkMax motor, double proportional, double intigral, double derivative) {
        var pidController = motor.getPIDController();
        pidController.setP(proportional);
        pidController.setI(intigral);
        pidController.setD(derivative);
    }

    private void setReferenceAngle(double referenceAngleRadians) {
        steerMotor.getPIDController().setReference(referenceAngleRadians, CANSparkMax.ControlType.kPosition);
    }

    private void setDriveVelocity(double metersPerSecond) {
       var voltage = metersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE;
       driveMotor.setVoltage(voltage);

  /*      if (metersPerSecond > 0) {
            driveMotor.set(.25);
        } else if (metersPerSecond < 0) {
            driveMotor.set(-.25);
        } else {
            driveMotor.set(0);
        } */
    }

    private void resetSteerRelativeEncoder() {
        steerRelativeEncoder.setPosition(getAbsoluteAngle());
    }

    private CANCoder createAbsoluteCanEncoder(int canId, double radiansOffset) {
        CANCoderConfiguration config = new CANCoderConfiguration();
        config.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
        config.magnetOffsetDegrees = Math.toDegrees(radiansOffset);
        config.sensorDirection = true;

        CANCoder encoder = new CANCoder(canId);
        encoder.configAllSettings(config, 250);
        return encoder;
    }

    // Reset the NEO's encoder periodically when the module is not rotating.
    // Sometimes (~5% of the time) when we initialize, the absolute encoder isn't fully set up, and we don't
    // end up getting a good reading. If we reset periodically this won't matter anymore.
    // This method tells us if its time to reset the relative encoder.
    private class RelativeEncoderResetTracker {
        private final int ENCODER_RESET_ITERATIONS = 500;
        private final double ENCODER_RESET_MAX_ANGULAR_VELOCITY = Math.toRadians(0.5);
        private int resetIteration = 0;

        public boolean isTimeToResetRelativeEncoder(double turingVelocity) {
            if (turingVelocity < ENCODER_RESET_MAX_ANGULAR_VELOCITY) {
                resetIteration += 1;
                if (resetIteration >= ENCODER_RESET_ITERATIONS) {
                    resetIteration = 0;
                    return true;
                }
            } else {
                resetIteration = 0;
            }
            return false;
        }
    }

    private String getSteerLogLabel(String propertyName) {
       return cfg.label + "-Steer-" + propertyName;
    }
    /*
    Unused function
    private String getDriveLogLabel(String propertyName) {
        return cfg.label + "-Drive-" + propertyName;
    }
    */
}
