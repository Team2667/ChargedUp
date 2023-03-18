// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 1.0;
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 1.0;

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 2;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 14;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = 4.344234;

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 5;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 6;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 12;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = 0.794602+Math.PI;

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 3;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 4;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 11;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = 3.393166;

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 7;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 8;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 13;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = 1.583068+Math.PI;

    public static final double WHEEL_REVOLUTIONS_PER_METER = 3.0;

    public static final int PCM_CAN_ID = 36;
    public static final double MAX_INPUT_SPEED = 1; //4.14528;
    public static final double PERCENTAGE_MAX_SPEED = 67.5;
    
    public static final boolean DRIVE_TRAIN_ENABLED = true;

    public static  double CAMERA_HEIGHT_METERS = Units.inchesToMeters(40.5);
    public static double TARGET_HEIGHT_METERS = Units.inchesToMeters(14.25);
    // Angle between horizontal and the camera.
    public static double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);

    public static double AUTO_RUN_TIME = 3.0;
    public static double AUTO_MAGNITUDE = 0.5;

    public static double ELEVATOR_MOE = .5;

    public static final int ElevatorLeftID = 31;//I wanted to call it guillotine and everyone including joey supported it
    public static final int ElevatorRightID = 32;//"No fun allowed" -Kent

    public static final int IntakeId = 40;

    public static final int wristId = 50;

    public static final double LeftP = 1;
    public static final double LeftI = 1;
    public static final double LeftD = 1;
    public static final double RightP = 1;
    public static final double RightI = 1;
    public static final double RightD = 1;

    public enum GamePieceType {
        Cone,
        Cube
    }

    public static final double CUBE_LOW = 0;
    public static final double CUBE_MID = 20;
    public static final double CUBE_HIGH = 30;
    public static final double HOME = 0;
    public static final double CONE_LOW = 10;
    public static final double CONE_MID = 30;
    public static final double CONE_HIGH = 40;
    

    public enum GoalPosition {
        low,
        mid,
        high,
        home,
        feeder,
        ground
    }
}