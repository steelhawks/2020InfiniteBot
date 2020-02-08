/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Shooter extends SubsystemBase
{
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
  public DoubleSolenoid falconCoolSol;

  //public LiveWindow lWindow = LiveWindow.getInstance();

  public Shooter()
  {
    // initializing motors
    this.shooterMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorOnePort);
    this.shooterMotorTwo = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorTwoPort);
    shooterMotorOne.setInverted(true);
    shooterMotorTwo.setInverted(false);

    // giving PID contants values
    this.kP = 0.7;
    this.kI = 0.6;
    this.kD = 0.4;
    this.kF = 0.14;
    this.bias = 0;
    
    // giving rpm a value equal to a port
    this.maxRPM = Robot.ROBOTMAP.shooterMaxRPM;

    // giving priors values
    this.integralPrior = 0;
    this.errorPrior = 0;

    // encoders
    this.shooterEncoderValue = 0;

    // falcons
    this.falconCoolSol = new DoubleSolenoid(0, 1);
  }

  // initalizing shooter+
  public void shootInit()
  { 
    this.shooterMotorOne.set(0);
    this.shooterMotorTwo.set(0);
  }
  public void shoot()
  {
    this.shooterMotorOne.set(output);
    this.shooterMotorTwo.set(output);
    
    // declaring local variables
    double error;
    double integral;
    double derivative;

    // calculating the target RPM
    double targetRPM = output * this.maxRPM;
    this.shooterMotorOne.set(targetRPM/this.maxRPM);
    this.shooterMotorTwo.set(targetRPM/this.maxRPM);

    // some math stuff
    error = (targetRPM - (shooterRPM() / (0.02 * 4096))) * (0.02 * 4096);
    integral = (error * 0.02);
    derivative = (error) / 0.02;
    this.output = (kP * error) + (kI * integral) + (kD * derivative) + bias;

    // // setting the calculated output speed and storing current data for the proceeding calculations
    System.out.println("Calculated output (based of off error): " + output);
    this.shooterMotorOne.set(this.output / this.maxRPM);
    this.shooterMotorTwo.set(this.output / this.maxRPM);
    shooterRPM();
  }

  public double shooterRPM()
  {
    // calculating/displaying rpm
    double shooterRPM;
    // shooterRPM = ((this.shooterMotorOne.getSelectedSensorPosition() - this.shooterEncoderValue) * 3000) / 4096;
    // System.out.println("Actual RPM: " + shooterRPM + " Encoder Position: " + this.shooterMotorOne.getSelectedSensorPosition());
    // this.shooterEncoderValue = this.shooterMotorOne.getSelectedSensorPosition();
    // SmartDashboard.putNumber("Shooter RPM", shooterRPM);
    // SmartDashboard.putBoolean("Test", true);
    shooterRPM = ((this.output / this.maxRPM) * 3000) / 4096;
    SmartDashboard.putNumber("ShooterRPM", shooterRPM);
    return shooterRPM;
  }

    // cool falcons
    public void coolFalcon()
  {
    if (falconCoolSol.get() == DoubleSolenoid.Value.kForward) 
    {
      this.falconCoolSol.set(DoubleSolenoid.Value.kReverse);
    } 
    else 
    {
      this.falconCoolSol.set(DoubleSolenoid.Value.kForward);
    }
    System.out.println("Cooled Falcons!");
  }
}