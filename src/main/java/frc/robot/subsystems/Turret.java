/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

import frc.util.subsystems.MechanicalSubsystem;

public class Turret extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX turretMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup turretMotorGroup;

  public int zeroPosition;

  // TURRET CONSTRUCTOR
  public Turret() {
    // SPARK MAX LEFT MOTORS
    this.turretMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.turretMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.turretMotorGroup = new SpeedControllerGroup(this.turretMotorOne);

    // ZERO POSITION
  }

  public void align()
  {
    if (Robot.VISION.getXPos() - Robot.VISION.getXPosOffset() < Robot.VISION.getXPosLeftLimit()) {
      System.out.println("right");
      this.turretMotorGroup.set(0.6* (Robot.VISION.getXPosDiff(Robot.VISION.getXPos() - Robot.VISION.getXPosOffset()) / 320) - (0.04));
      System.out.println(0.6 * (Robot.VISION.getXPosDiff(Robot.VISION.getXPos() - Robot.VISION.getXPosOffset()) / 320) - (0.04));
    } else if (Robot.VISION.getXPos() - Robot.VISION.getXPosOffset() > Robot.VISION.getXPosRightLimit()) {
      System.out.println("left");
      this.turretMotorGroup.set(-0.6 * (Robot.VISION.getXPosDiff(Robot.VISION.getXPos() - Robot.VISION.getXPosOffset()) / 320) + (0.04));
      System.out.println(-0.6 * (Robot.VISION.getXPosDiff(Robot.VISION.getXPos() - Robot.VISION.getXPosOffset()) / 320) + (0.04));
    } else {
      stop();
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