/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class Shooter extends MechanicalSubsystem {
  // declaring motors
  public WPI_TalonSRX shooterMotorOne;
  public WPI_TalonSRX shooterMotorTwo;

  // declaring PID constants
  public double kP;
  public double kI;
  public double kD;
  public double kF;
  public double bias;
  public double output;

  // declaring rpm and PID variables
  public int maxRPM;
  public double integralPrior;
  public double errorPrior;

  // encoder values
  public int shooterEncoderValue;

  // solenoid
  //public DoubleSolenoid falconCoolSol;

  // is the shooter spooled?
  public boolean isSpooled;

  public Shooter() {
    // initializing motors
    this.shooterMotorOne = new WPI_TalonSRX(Robot.ROBOT_MAP.shooterMotorOnePort);
    this.shooterMotorTwo = new WPI_TalonSRX(Robot.ROBOT_MAP.shooterMotorTwoPort);

    // giving PID contants values
    this.kP = 0.7;
    this.kI = 0.6;
    this.kD = 0.4;
    this.kF = 0.14;
    this.bias = 0;

    // giving rpm a value equal to a port
    this.maxRPM = Robot.ROBOT_MAP.shooterMaxRPM;

    // giving priors values
    this.integralPrior = 0;
    this.errorPrior = 0;
    this.output = 0;

    // encoders
    this.shooterEncoderValue = 0;

    // is the shooter spooled?
    this.isSpooled = false;

    // setting PID

    this.shooterMotorOne.configFactoryDefault();
    this.shooterMotorTwo.configFactoryDefault();

    this.shooterMotorOne.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    this.shooterMotorTwo.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 30);
    
    shooterMotorOne.setInverted(true);
    shooterMotorTwo.setInverted(false);

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
  }

  // initalizing shooter
  public boolean stop() {
    System.out.println("stopping");
    this.shooterMotorOne.set(ControlMode.PercentOutput, 0);
    this.shooterMotorTwo.set(ControlMode.PercentOutput, 0);
    this.isSpooled = false;
    return true;
  }

  public void spool(double output) {
    this.shooterMotorOne.set(ControlMode.PercentOutput, output);
    this.shooterMotorTwo.set(ControlMode.PercentOutput, output);
    if (this.shooterMotorOne.get() >= output)
    {
      this.isSpooled = true;
    }
  }

  public void shoot(double output) {
    this.output = output;
    double speed = shooterRPM();
    this.shooterMotorOne.set(ControlMode.PercentOutput, speed);
    this.shooterMotorTwo.set(ControlMode.PercentOutput, speed);
  }

  public double shooterRPM() {
    // calculating/displaying rpm
    double shooterRPM;
    shooterRPM = ((this.output / this.maxRPM) * 3000) / 4096;
    SmartDashboard.putNumber("ShooterRPM", shooterRPM);
    return shooterRPM;
  }

  public void ping() {}

  public void smartDashboard() {}

  public boolean isAlive() {
    return this.shooterMotorOne.isAlive() && this.shooterMotorTwo.isAlive();
  }
}