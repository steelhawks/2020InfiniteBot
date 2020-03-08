/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Storage extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX storageMotorOne;
  public final WPI_TalonSRX storageMotorThree;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup storageMotorGroup;
  public final SpeedControllerGroup storageMotorGroupTwo;


  // DRIVETRAIN CONSTRUCTOR
  public Storage() {
    // SPARK MAX LEFT MOTORS
    this.storageMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.storageMotorOnePort);
    this.storageMotorThree = new WPI_TalonSRX(Robot.ROBOT_MAP.storageMotorThreePort);

    this.storageMotorOne.setInverted(false);
    this.storageMotorThree.setInverted(false);

    // SPEED CONTROLLER GROUPS
    this.storageMotorGroup = new SpeedControllerGroup(this.storageMotorOne);
    this.storageMotorGroupTwo = new SpeedControllerGroup(this.storageMotorThree);

    configureMotors();
  }

  public void moveBalls(boolean isForward) {
    if (isForward) {
      this.storageMotorGroup.set(-Robot.ROBOT_MAP.storageSpeedOne);
      this.storageMotorGroupTwo.set(-Robot.ROBOT_MAP.storageSpeedTwo);
    } else {
      this.storageMotorGroup.set(Robot.ROBOT_MAP.storageSpeedOne);
      this.storageMotorGroupTwo.set(Robot.ROBOT_MAP.storageSpeedTwo);
    }
  }

  public void stopStorage() {
    this.storageMotorGroup.set(0.0);
    this.storageMotorGroupTwo.set(0.0);
  }

  public void configureMotors() {
    this.storageMotorOne.configFactoryDefault();
    this.storageMotorThree.configFactoryDefault();
    
    this.storageMotorOne.setNeutralMode(NeutralMode.Coast);
    this.storageMotorThree.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Intake Motor One Speed", this.storageMotorOne.get());
  }

  public boolean isAlive() {
    return storageMotorOne.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.storageMotorOne.stopMotor();
    this.storageMotorThree.stopMotor();
    return true;
  }
}