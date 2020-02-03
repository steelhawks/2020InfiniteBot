/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Shooter extends SubsystemBase
{
    // declaring motors
    public WPI_TalonSRX shooterMotorOne;
    public WPI_TalonSRX shooterMotorTwo;
    public WPI_TalonSRX feederMotorOne;
    public SpeedControllerGroup feederMotorController;

    // declaring PID constants
    public double kP;
    public double kI;
    public double kD;
    public double kPfeed; 
    public double kIfeed;
    public double kDfeed;
    public double bias;

    // declaring rpm and PID variables
    public int maxRPM;
    public double integralPrior;
    public double errorPrior;

    // encoder values
    public int shooterEncoderValue;
    public int feederEncoderValue;

    // solenoid
    public DoubleSolenoid falconCoolSol;

    //public LiveWindow lWindow = LiveWindow.getInstance();

    public Shooter()
    {
        // initializing motors
        this.shooterMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorOnePort);
        this.shooterMotorTwo = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorTwoPort);
        this.feederMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.feedMotorOnePort);
        this.feederMotorController = new SpeedControllerGroup(this.feederMotorOne);

        // // setting motors to follow 1st
        // this.shooterMotorTwo.follow(this.shooterMotorOne);

        // // setting motor direction
        // this.shooterMotorOne.setInverted(false);
        // this.shooterMotorTwo.setInverted(InvertType.OpposeMaster);
        shooterMotorOne.setInverted(true);
        shooterMotorTwo.setInverted(false);

        // giving PID contants values
        this.kP = 0.5;
        this.kI = 0.4;
        this.kD = 0;
        this.bias = 0;
        this.kPfeed = 0.6;
        this.kIfeed = 0.5;
        this.kDfeed = 0;
       
        // giving rpm a value equal to a port
        this.maxRPM = Robot.ROBOTMAP.shooterMaxRPM;

        // giving priors values
        this.integralPrior = 0;
        this.errorPrior = 0;

        // encoders
        this.shooterEncoderValue = 0;
        this.feederEncoderValue = 0;

        // falcons
        this.falconCoolSol = new DoubleSolenoid(0, 1);
    }
    // initalizing shooter+
    public void shootInit()
    { 
      this.shooterMotorOne.set(0);
      this.shooterMotorTwo.set(0);
    }
    public void shoot(double output)
    {
       // declaring local variables
      double error;
      double integral;
      double derivative;

      // // calculating the target RPM
      double feederOut = output;
      double targetRPM = output * this.maxRPM;
      double feederTarget = feederOut * Robot.ROBOTMAP.feederMaxRPM;
      // this.shooterMotorOne.set(targetRPM/this.maxRPM);
      // this.shooterMotorTwo.set(targetRPM/this.maxRPM);


      // some math stuff
      error = (targetRPM - (shooterRPM() / (0.02 * 4096))) * (0.02 * 4096);
      integral = (error * 0.02);
      derivative = (error) / 0.02;
      output = (kP * error) + (kI * integral) + (kD * derivative) + bias;

      // feeder PID
      double feederError = (feederTarget - (feederRPM() / (0.02 * 4096))) * (0.02 * 4096);
      double feederIntegral = (feederError * 0.02);
      double feederDerivative = (feederError) / 0.02;
      feederOut = (kPfeed * feederError) + (kIfeed * feederIntegral) + (kDfeed * feederDerivative) + bias;
 
      // setting the calculated output speed and storing current data for the proceeding calculations
      System.out.println("Calculated output (based of off error): " + output);
      this.shooterMotorOne.set(output / this.maxRPM);
      this.shooterMotorTwo.set(output / this.maxRPM);
      this.feederMotorController.set((-feederOut / Robot.ROBOTMAP.feederMaxRPM));

        // this.shooterMotorOne.pidWrite(-output);
        // this.shooterMotorTwo.pidWrite(output);
        // this.feederMotorOne.pidWrite(-output);
      shooterRPM();
    }

    public double shooterRPM()
    {
        // calculating/displaying rpm
        double shooterRPM;
        shooterRPM = ((this.shooterMotorOne.getSelectedSensorPosition() - this.shooterEncoderValue) * 3000) / 4096;
        System.out.println("Actual RPM: " + shooterRPM + " Encoder Position: " + this.shooterMotorOne.getSelectedSensorPosition());
        this.shooterEncoderValue = this.shooterMotorOne.getSelectedSensorPosition();
        SmartDashboard.putNumber("Shooter RPM", shooterRPM);
        SmartDashboard.putBoolean("Test", true);
        return shooterRPM;
    }

    public double feederRPM()
    {
      double feederRPM;
      feederRPM = ((this.feederMotorOne.getSelectedSensorPosition() - this.feederEncoderValue) * 3000) / 4096;
      System.out.println("Feeder RPM: " + feederRPM + "Encoder Position: " + this.feederMotorOne.getSelectedSensorPosition());
      this.feederEncoderValue = this.feederMotorOne.getSelectedSensorPosition();
      SmartDashboard.putNumber("Feeder RPM", feederRPM);
      SmartDashboard.putBoolean("Test", true);
      return feederRPM;
    }

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