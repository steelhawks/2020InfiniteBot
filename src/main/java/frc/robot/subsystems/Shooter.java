/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class Shooter extends SubsystemBase
{
    // declaring motors
    public WPI_TalonSRX shooterMotorOne;
    public WPI_TalonSRX shooterMotorTwo;
    public WPI_TalonSRX shooterMotorThree;
    public WPI_TalonSRX shooterMotorFour;

    // declaring PID constants
    public double kP;
    public double kI;
    public double kD;
    public double bias;

    // declaring rpm and PID variables
    public int maxRPM;
    public double integralPrior;
    public double errorPrior;

    public int encoderValue;

    // solenoid
    public DoubleSolenoid falconCoolSol;

    //public LiveWindow lWindow = LiveWindow.getInstance();

    public Shooter()
    {
        // initializing motors
        this.shooterMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorOnePort);
        this.shooterMotorTwo = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorTwoPort);
        this.shooterMotorThree = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorThreePort);
        this.shooterMotorFour = new WPI_TalonSRX(Robot.ROBOTMAP.shootMotorFourPort);

        // setting motors to follow 1st
        this.shooterMotorTwo.follow(this.shooterMotorOne);
        this.shooterMotorThree.follow(this.shooterMotorOne);
        this.shooterMotorFour.follow(this.shooterMotorOne);

        // setting motor direction
        this.shooterMotorOne.setInverted(false);
        this.shooterMotorTwo.setInverted(InvertType.FollowMaster);
        this.shooterMotorThree.setInverted(InvertType.OpposeMaster);
        this.shooterMotorFour.setInverted(InvertType.OpposeMaster);
       
        // giving PID contants values
        this.kP = 0.5;
        this.kI = 0.4;
        this.kD = 0;
        this.bias = 0;
       
        // giving rpm a value equal to a port
        this.maxRPM = Robot.ROBOTMAP.shooterMaxRPM;

        // giving priors values
        this.integralPrior = 0;
        this.errorPrior = 0;

        this.encoderValue = 0;

        
        this.falconCoolSol = new DoubleSolenoid(0, 1);
    }

    public void shoot(double output)
    {
        // declaring local variables
        double error;
        double integral;
        double derivative;

        // calculating the target RPM
        double targetRPM = output * this.maxRPM;
        // this.shooterMotorOne.set(targetRPM/this.maxRPM);

        // some math stuff
        error = (targetRPM - (rPM() / (0.02 * 4096))) * (0.02 * 4096);
        integral = (error * 0.02);
        derivative = (error) / 0.02;
        output = (kP * error) + (kI * integral) + (kD * derivative) + bias;

        // setting the calculated output speed and storing current data for the proceeding calculations
        System.out.println("Calculated output (based of off error): " + output);
        this.shooterMotorOne.set(output / this.maxRPM);
        errorPrior = error;
        this.integralPrior = integral;
    }

    public double rPM()
    {
        // calculating/displaying rpm
        double rpm;
        rpm = ((this.shooterMotorOne.getSelectedSensorPosition() - this.encoderValue) * 3000) / 4096;
        System.out.println("Actual RPM: " + rpm + " Encoder Position: " + this.shooterMotorOne.getSelectedSensorPosition());
        this.encoderValue = this.shooterMotorOne.getSelectedSensorPosition();
        SmartDashboard.putNumber("RPM", rpm);
        SmartDashboard.putBoolean("Test", true);
        return rpm;
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