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

public class Storage extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX storageMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup storageMotorGroup;

  // DRIVETRAIN CONSTRUCTOR
  public Storage() {
    // SPARK MAX LEFT MOTORS
    this.storageMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.storageMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.storageMotorGroup = new SpeedControllerGroup(this.storageMotorOne);
  }

  public void moveBalls(boolean isForward) {
    if (isForward) {
      this.storageMotorGroup.set(Robot.ROBOT_MAP.storageSpeed);
    } else {
      this.storageMotorGroup.set(-Robot.ROBOT_MAP.storageSpeed);
    }
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
    return true;
  }
}