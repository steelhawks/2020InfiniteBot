/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import frc.util.subsystems.MechanicalSubsystem;

public class Turret extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX turretMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup turretMotorGroup;

  // POSITIONS
  public int zeroPosition;
  public int quarterPosition;

  // LIMIT SWITCHES
  public DigitalInput leftLimitSwitch;
  public DigitalInput rightLimitSwitch;

  // Testing
  public boolean testing;
  
  // TURRET CONSTRUCTOR
  public Turret() {
    // SPARK MAX LEFT MOTORS
    this.turretMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.turretMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.turretMotorGroup = new SpeedControllerGroup(this.turretMotorOne);

    // POSITIONS
    this.zeroPosition = Robot.ROBOT_MAP.turretZeroPos;
    this.quarterPosition = Robot.ROBOT_MAP.turretQuarterPos;

    // LIMIT SWITCHES
    this.leftLimitSwitch = new DigitalInput(Robot.ROBOT_MAP.turretLeftLimitSwitchPort);
    this.rightLimitSwitch = new DigitalInput(Robot.ROBOT_MAP.turretRightLimitSwitchPort);

    // TESTING
    this.testing = false;
  }

  public void goTo(int position) {
    if (position < this.turretMotorOne.getSelectedSensorPosition() && !this.leftLimitSwitch.get()) {
      this.turretMotorOne.set(
          -0.15 * ((this.turretMotorOne.getSelectedSensorPosition() / Math.abs((this.zeroPosition - this.quarterPosition ) / 90)) / 45) + (-0.125));
    } else if (position > this.turretMotorOne.getSelectedSensorPosition() && !this.rightLimitSwitch.get()) {
      this.turretMotorOne.set(
          0.15 * ((this.turretMotorOne.getSelectedSensorPosition() / Math.abs((this.zeroPosition - this.quarterPosition ) / 90)) / 45) + (0.125));
    } else {
      this.turretMotorOne.set(0.0);
    }
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Turret Motor One Speed", this.turretMotorOne.get());
  }

  public boolean isAlive() {
    return turretMotorOne.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.turretMotorOne.stopMotor();
    return true;
  }
}