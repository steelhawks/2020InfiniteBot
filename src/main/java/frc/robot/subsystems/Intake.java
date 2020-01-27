/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Robot;

public class Intake extends SubsystemBase 
{
  //SPARK MAX MOTOR
  public final WPI_TalonFX intakeMotorOne;

  //SPEED CONTROLLER GROUP
  public final SpeedControllerGroup intakeMotorGroup;

  //DRIVETRAIN CONSTRUCTOR
  public Intake() 
  {
    //SPARK MAX LEFT MOTORS
    this.intakeMotorOne = new WPI_TalonFX(Robot.ROBOTMAP.intakeMotorPort);
    
    //SPEED CONTROLLER GROUPS
    this.intakeMotorGroup = new SpeedControllerGroup(this.intakeMotorOne);
  }

  //STOP MOTOR

  public void StopMotors() 
  {
    intakeMotorOne.set(0);
  }
  
  //CONVERT AN INT SPEED INTO A DECIMAL SPEED
  public double decimalSpeed(final double speed)
  {
    
    return ((int)(((speed + 350) / 700.0) * 100) / 100.0);
  
  }

  public void IntakeButton() 
  {
    if(intakeMotorOne.get() == 0) 
    {
      intakeMotorOne.set(0.8);
    } 
    
    else 
    {
      intakeMotorOne.set(0);
    }
  }

}