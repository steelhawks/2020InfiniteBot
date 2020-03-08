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

public class Climber extends MechanicalSubsystem {
  // TALON SRX MOTOR CONTROLLER
  public final WPI_TalonSRX climberMotorOne;

  // SPEED CONTROLLER GROUP
  public final SpeedControllerGroup climberMotorGroup;

  // SOLENOID
  public DoubleSolenoid climberSol = new DoubleSolenoid(Robot.ROBOT_MAP.climberSolOnPort,
      Robot.ROBOT_MAP.climberSolOffPort);
  public DoubleSolenoid climberSafetySol = new DoubleSolenoid(Robot.ROBOT_MAP.safetySolOnPort,
      Robot.ROBOT_MAP.safetySolOffPort);

  public Climber() {
    // TALON SRX MOTOR CONTROLLER
    this.climberMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.climbMotorOnePort);

    // SPEED CONTROLLER GROUP
    this.climberMotorGroup = new SpeedControllerGroup(this.climberMotorOne);

    configureMotors();
  }

  public void toggleSolenoid() {
    if (this.climberSol.get().equals(DoubleSolenoid.Value.kForward)) {
      retractSolenoid();
    } else {
      extendSolenoid();
    }
  }

  public void extendSolenoid() {
    this.climberSol.set(DoubleSolenoid.Value.kForward);
  }

  public void retractSolenoid() {
    this.climberSol.set(DoubleSolenoid.Value.kReverse);
  }

  public void toggleSafetySolenoid() {
    if (this.climberSafetySol.get().equals(DoubleSolenoid.Value.kForward)) {
      retractSafetySolenoid();
    } else {
      extendSafetySolenoid();
    }
  }

  public void extendSafetySolenoid() {
    this.climberSafetySol.set(DoubleSolenoid.Value.kForward);
  }

  public void retractSafetySolenoid() {
    this.climberSafetySol.set(DoubleSolenoid.Value.kReverse);
  }

  public void rollWinch(boolean isForward) {
    System.out.println("winching");
    if (isForward) {
      this.climberMotorGroup.set(Robot.ROBOT_MAP.climberSpeed);
    } else {
      this.climberMotorGroup.set(-Robot.ROBOT_MAP.climberSpeed);
    }
  }

  public void configureMotors() {
    this.climberMotorOne.configFactoryDefault();

    this.climberMotorOne.setNeutralMode(NeutralMode.Coast);
  }

  public void smartDashboard() {
    SmartDashboard.putBoolean("Climber Solenoid State",
        (this.climberSol.get() == DoubleSolenoid.Value.kForward) ? true : false);
    SmartDashboard.putNumber("Climber Motor One Speed", this.climberMotorOne.get());
  }

  public boolean isAlive() {
    return climberMotorOne.isAlive();
  }

  public void stopClimb() {
    this.climberMotorGroup.set(0.0);
  }

  public void ping() {
  }

  public boolean stop() {
    this.climberMotorOne.stopMotor();
    return true;
  }
}
