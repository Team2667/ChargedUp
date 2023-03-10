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

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 5;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 6;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 11;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = 5.451768;

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 3;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 4;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 12;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = 1.696583;

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 7;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 8;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 13;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = 1.382117;

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 1;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 2;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 14;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = 1.6873793465;

    public static final double WHEEL_REVOLUTIONS_PER_METER = 3.0;

    public static final int PCM_CAN_ID = 36;
    public static final double MAX_INPUT_SPEED = 1; //4.14528;
    public static final double PERCENTAGE_MAX_SPEED = 67.5;

    public static final int PNEU_GRAB_CHNL = 0;
    

    public static final boolean DRIVE_TRAIN_ENABLED = true;
    public static final boolean JAWS_OF_LIFE_ENABLED = true;    
    public static final boolean PIVOT_ENABLED = true;
    public static final boolean PINCH_ENABLED = true;
    public static final boolean ARM_EXTENDER_ENABLED = true;

    public static  double CAMERA_HEIGHT_METERS = Units.inchesToMeters(18.375);
    public static double TARGET_HEIGHT_METERS = Units.inchesToMeters(14.25);
    // Angle between horizontal and the camera.
    public static double CAMERA_PITCH_RADIANS = Units.degreesToRadians(0);
    public static final int rotatorMotor=16;
    public static int ARM_CANID=17;

    public static int LEFT_PINCHY_ID=18;
    public static int RIGHT_PINCHY_ID=19;

    public static double PINCHY_PERCENTAGE=20;

    public static double ARM_ROTATIONS[]={6,9,4.2};

    public static double PIVOT_CUBE_ROT_LOW=14;
    public static double PIVOT_CUBE_ROT_MEDIUM=46;
    public static double PIVOT_CUBE_ROT_HIGH=57;

    public static double PIVOT_CONE_ROT_LOW=14;
    public static double PIVOT_CONE_ROT_MEDIUM=49;
    public static double PIVOT_CONE_ROT_HIGH=61;
    public static double PIVOT_ROT_HOME=7;
    public static double PIVOT_ROT_FEEDER = 46;

    public static double EXTEND_LOW = 10;
    public static double EXTEND_MEDIUM = 35;
    public static double EXTEND_HIGH = 94;
    public static double EXTEND_FEEDER = 10;
    public static double EXTEND_HOME = 0;

    public static double AUTO_RUN_TIME = 3.0;
    public static double AUTO_MAGNITUDE = 0.5;

    public enum GoalPos {
        low,
        med,
        high,
        home,
        feeder
    }



    

    public static final double ROTATIONAL_LENIENCY=.5;
    public static final double PROTRUSION_LENIENCY=1;
    //Obviously these are not the actual lengths replace these later
}