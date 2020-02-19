/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.robot.Robot;
import frc.util.pathcorder.JoystickRecorder;
import frc.util.subsystems.MechanicalSubsystem;

public class Drivetrain extends MechanicalSubsystem {
  // CANSPARK MAX LEFT MOTORS
  public final WPI_TalonSRX drivetrainLeftMotorOne;
  public final WPI_TalonSRX drivetrainLeftMotorTwo;
  public final WPI_TalonSRX drivetrainLeftMotorThree;

  // CANSPARK MAX RIGHT MOTORS
  public final WPI_TalonSRX drivetrainRightMotorOne;
  public final WPI_TalonSRX drivetrainRightMotorTwo;
  public final WPI_TalonSRX drivetrainRightMotorThree;

  // SPEED CONTROLLER GROUPS
  public final SpeedControllerGroup drivetrainLeftMotorGroup;
  public final SpeedControllerGroup drivetrainRightMotorGroup;

  // Differntial Drive
  public final DifferentialDrive diffDrive;

  // NAX MXP GYRO
  public final AHRS gyro;

  // SHIFTING SOLENOIDS
  public DoubleSolenoid shiftSol = new DoubleSolenoid(Robot.ROBOT_MAP.drivetrainSolOnPort,
      Robot.ROBOT_MAP.drivetrainSolOffPort);

  // DIRECTION
  public boolean isForward;

  // PATHCORDER
  public int count;

  // DRIVETRAIN CONTRUCTOR
  public Drivetrain() {
    // CANSPARK MAX LEFT MOTORS
    this.drivetrainLeftMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainLeftMotorOnePort);
    this.drivetrainLeftMotorTwo = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainLeftMotorTwoPort);
    this.drivetrainLeftMotorThree = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainLeftMotorThreePort);

    // CANSPARK MAX RIGHT MOTORS
    this.drivetrainRightMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainRightMotorOnePort);
    this.drivetrainRightMotorTwo = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainRightMotorTwoPort);
    this.drivetrainRightMotorThree = new WPI_TalonSRX(Robot.ROBOT_MAP.drivetrainRightMotorThreePort);

    // SPEEDCONTROLLER GROUPS
    this.drivetrainLeftMotorGroup = new SpeedControllerGroup(this.drivetrainLeftMotorOne, this.drivetrainLeftMotorTwo,
        this.drivetrainLeftMotorThree);
    this.drivetrainRightMotorGroup = new SpeedControllerGroup(this.drivetrainRightMotorOne,
        this.drivetrainRightMotorTwo, this.drivetrainRightMotorThree);

    // DIFFERENTIAL DRIVE
    this.diffDrive = new DifferentialDrive(this.drivetrainLeftMotorGroup, this.drivetrainRightMotorGroup);

    // NAVX MXP
    this.gyro = new AHRS(SPI.Port.kMXP);

    //
    //
    //

    // DIRECTION & FOLLOWER
    this.isForward = true;

    // PATHCORDER
    this.count = 0;

    configureMotors();
  }

  // DRIVING METHOD
  public void arcadeDrive(Joystick stick) {
    double y = stick.getY();
    double rotate = stick.getTwist();
    if (this.isForward) {
      // nothing
    } else {
      y = -y;
    }
    this.diffDrive.arcadeDrive(y, -rotate);
    if (Robot.RECORDER.isRecording) {
      count++;
      Robot.RECORDER.recordJoystick(new JoystickRecorder(y, rotate, false, count));
    }
  }

  public void arcadeDrive(double joyY, double rotation) {
    double y = joyY;
    double rotate = rotation;
    if (Robot.DRIVETRAIN.isForward) {
      Robot.DRIVETRAIN.diffDrive.arcadeDrive(y, -rotate);
    } else {
      Robot.DRIVETRAIN.diffDrive.arcadeDrive(-y, -rotate);
    }
  }

  public void rotate(double speed) {
    this.diffDrive.arcadeDrive(0, speed);
  }

  public void reverseDirection() {
    if (!this.isForward) {
      this.isForward = true;
    } else {
      this.isForward = false;
    }
    System.out.println("Motion reversed!");
  }

  // SHIFTING METHOD
  public void shiftGear() {
    if (this.shiftSol.get() == DoubleSolenoid.Value.kForward) {
      highGear();
    } else {
      lowGear();
    }
    System.out.println("Shifted gears!");
  }

  public void lowGear() {
    this.shiftSol.set(DoubleSolenoid.Value.kForward);
  }

  public void highGear() {
    this.shiftSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void coolFalcons() {
    // if (falconCoolSol.get() == DoubleSolenoid.Value.kForward) {
    // this.falconCoolSol.set(DoubleSolenoid.Value.kReverse);
    // } else {
    // this.falconCoolSol.set(DoubleSolenoid.Value.kForward);
    // }
    System.out.println("Cooled Falcons!");
  }

  public void configureMotors() {
    this.drivetrainLeftMotorOne.configFactoryDefault();
    this.drivetrainLeftMotorTwo.configFactoryDefault();
    this.drivetrainLeftMotorThree.configFactoryDefault();
    this.drivetrainRightMotorOne.configFactoryDefault();
    this.drivetrainRightMotorTwo.configFactoryDefault();
    this.drivetrainRightMotorThree.configFactoryDefault();
  }

  public void smartDashboard() {
  }

  public boolean stop() {
    this.drivetrainLeftMotorGroup.set(0.0);
    this.drivetrainRightMotorGroup.set(0.0);
    return true;
  }

  public void ping() {
  }

  public boolean isAlive() {
    return this.drivetrainLeftMotorOne.isAlive() && this.drivetrainLeftMotorTwo.isAlive()
        && this.drivetrainLeftMotorThree.isAlive() && this.drivetrainRightMotorOne.isAlive()
        && this.drivetrainRightMotorTwo.isAlive() && this.drivetrainRightMotorThree.isAlive();
  }
}
