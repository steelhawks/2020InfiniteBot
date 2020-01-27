/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.AnalogInput;

public class Storage extends SubsystemBase 
{
  //SPARK MAX MOTOR
  public final WPI_TalonFX storageMotorOne;

  //SPEED CONTROLLER GROUP
  public final SpeedControllerGroup storageMotorGroup;

  //IR Sensor
  public final AnalogInput iRSensor;

  //DRIVETRAIN CONSTRUCTOR
  public Storage() 
  {
    //SPARK MAX LEFT MOTORS
    this.storageMotorOne = new WPI_TalonFX(Robot.ROBOTMAP.storageMotorPort);
    
    //SPEED CONTROLLER GROUPS
    this.storageMotorGroup = new SpeedControllerGroup(this.storageMotorOne);

    //IR Sensor
    this.iRSensor = new AnalogInput(0);
  }

  //CONVERT AN INT SPEED INTO A DECIMAL SPEED
  public void moveBalls()
  {
    if(this.iRSensor.getValue() <= 500)
    {
      storageMotorGroup.set(0);
    }
    else
    {
      storageMotorGroup.set(0.8);
    }
  }

  public void stop()
  {
    storageMotorGroup.set(0);
  }
} 
