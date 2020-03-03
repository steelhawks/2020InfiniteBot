/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import frc.robot.Robot;
import frc.util.Waiter;
import frc.util.subsystems.MechanicalSubsystem;

public class Shooter extends MechanicalSubsystem {
  // declaring motors
  public WPI_TalonFX shooterMotorOne;
  public WPI_TalonFX shooterMotorTwo;

  // declaring PID constants
  public double kP;
  public double kI;
  public double kD;
  public double kF;

  // is the shooter spooled?
  public Waiter spoolTime;
  public boolean isSpooled;

  public Shooter() {
    // initializing motors
    this.shooterMotorOne = new WPI_TalonFX(Robot.ROBOT_MAP.shooterMotorOnePort);
    this.shooterMotorTwo = new WPI_TalonFX(Robot.ROBOT_MAP.shooterMotorTwoPort);

    // giving PID contants values
    // this.kP = 0.7;
    // this.kI = 0.6;
    // this.kD = 0.4;
    // this.kF = 0.14;
    // this.bias = 0;
    // this.shooterVelocity = 0;
    this.kP = 0.8;
    this.kI = 0.4;
    this.kD = 0.25;
    this.kF = 0.2;

    // is the shooter spooled?
    this.spoolTime = new Waiter(250);
    this.isSpooled = false;

    // setting PID

    this.shooterMotorOne.configFactoryDefault();
    this.shooterMotorTwo.configFactoryDefault();

    this.shooterMotorOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    this.shooterMotorTwo.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);

    shooterMotorOne.setInverted(false);
    shooterMotorTwo.setInverted(true);

    this.shooterMotorOne.setSensorPhase(true);
    this.shooterMotorTwo.setSensorPhase(true);

    this.shooterMotorOne.configNominalOutputForward(0, 30);
    this.shooterMotorOne.configNominalOutputReverse(0, 30);
    this.shooterMotorOne.configPeakOutputForward(1, 30);
    this.shooterMotorOne.configPeakOutputReverse(-1, 30);
    this.shooterMotorTwo.configNominalOutputForward(0, 30);
    this.shooterMotorTwo.configNominalOutputReverse(0, 30);
    this.shooterMotorTwo.configPeakOutputForward(1, 30);
    this.shooterMotorTwo.configPeakOutputReverse(-1, 30);

    this.shooterMotorOne.config_kF(0, this.kF, 30);
    this.shooterMotorOne.config_kP(0, this.kP, 30);
    this.shooterMotorOne.config_kI(0, this.kI, 30);
    this.shooterMotorOne.config_kD(0, this.kD, 30);
    this.shooterMotorTwo.config_kF(0, this.kF, 30);
    this.shooterMotorTwo.config_kP(0, this.kP, 30);
    this.shooterMotorTwo.config_kI(0, this.kI, 30);
    this.shooterMotorTwo.config_kD(0, this.kD, 30);
    
    configureMotors();
  }

  public boolean stop() {
    System.out.println("stopping");
    this.shooterMotorOne.set(ControlMode.PercentOutput, 0);
    this.shooterMotorTwo.set(ControlMode.PercentOutput, 0);
    this.spoolTime.reset();
    this.isSpooled = false;
    return true;
  }

  public void spool(double output) {
    this.shooterMotorOne.set(ControlMode.PercentOutput, output);
    this.shooterMotorTwo.set(ControlMode.PercentOutput, output);
    System.out
        .println("Motor one Spool speed " + this.shooterMotorOne.getSensorCollection().getIntegratedSensorVelocity());
    System.out
        .println("Motor two spool speed " + this.shooterMotorOne.getSensorCollection().getIntegratedSensorVelocity());

    if (this.spoolTime.getIsExpired()) {
      this.isSpooled = true;
    }
    this.spoolTime.increment();

  }

  public void shoot(double output) {
    this.shooterMotorOne.set(ControlMode.PercentOutput, output);
    System.out.println("Motor one Shot speed " + this.shooterMotorOne.getSelectedSensorVelocity());
    this.shooterMotorTwo.set(ControlMode.PercentOutput, output);
    System.out.println("Motor two shot speed " + this.shooterMotorOne.getSelectedSensorVelocity());

  }

  public void configureMotors() {
    this.shooterMotorOne.configFactoryDefault();
    this.shooterMotorTwo.configFactoryDefault();
    
    this.shooterMotorOne.setNeutralMode(NeutralMode.Coast);
    this.shooterMotorTwo.setNeutralMode(NeutralMode.Coast);
  }

  public void ping() {
  }

  public void smartDashboard() {
  }

  public boolean isAlive() {
    return this.shooterMotorOne.isAlive() && this.shooterMotorTwo.isAlive();
  }
}