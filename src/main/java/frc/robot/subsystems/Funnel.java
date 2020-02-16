/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Funnel extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX funnelMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup funnelMotorGroup;

  // DRIVETRAIN CONSTRUCTOR
  public Funnel() {
    // SPARK MAX LEFT MOTORS
    this.funnelMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.funnelMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.funnelMotorGroup = new SpeedControllerGroup(this.funnelMotorOne);
  }

  public void moveBalls(boolean isForward) {
    if (isForward) {
      this.funnelMotorGroup.set(Robot.ROBOT_MAP.funnelSpeed);
    } else {
      this.funnelMotorGroup.set(-Robot.ROBOT_MAP.funnelSpeed);
    }
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Intake Motor One Speed", this.funnelMotorOne.get());
  }

  public boolean isAlive() {
    return funnelMotorOne.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.funnelMotorOne.stopMotor();
    return true;
  }
}