/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

import frc.util.subsystems.MechanicalSubsystem;

public class Intake extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX intakeMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup intakeMotorGroup;

  // SOLENOID
  public DoubleSolenoid intakeSol;

  // DRIVETRAIN CONSTRUCTOR
  public Intake() {
    // TALON SRX MOTOR CONTROLLER
    this.intakeMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.intakeMotorOnePort);

    // SPEED CONTROLLER GROUPS
    this.intakeMotorGroup = new SpeedControllerGroup(this.intakeMotorOne);

    // SOLENOID
    this.intakeSol = new DoubleSolenoid(Robot.ROBOT_MAP.intakeSolOnPort, Robot.ROBOT_MAP.intakeSolOffPort);
    
    configureMotors();
  }

  public void spinRoller(boolean isForward) {
    System.out.println("spinning motors");
    if (isForward) {
      this.intakeMotorGroup.set(Robot.ROBOT_MAP.intakeSpeed);
    } else {
      this.intakeMotorGroup.set(-Robot.ROBOT_MAP.intakeSpeed);
    }
  }

  public void spinRoller(double speed) {
    System.out.println("2");
    this.intakeMotorGroup.set(speed);
  }

  public void togglePosition() {
    if (this.intakeSol.get() == DoubleSolenoid.Value.kForward) {
      up();
    } else {
      down();
    }
    System.out.println("Shifted gears!");
  }

  public void up() {
    System.out.println("up");
    this.intakeSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void down() {
    System.out.println("down");
    this.intakeSol.set(DoubleSolenoid.Value.kForward);
  }

  public void configureMotors() {
    this.intakeMotorOne.configFactoryDefault();

    this.intakeMotorOne.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
    SmartDashboard.putNumber("Intake Motor One Speed", this.intakeMotorOne.get());
  }

  public boolean isAlive() {
    return intakeMotorOne.isAlive();
  }

  public void ping() {
  }

  public boolean stop() {
    this.intakeMotorOne.stopMotor();
    return true;
  }
}