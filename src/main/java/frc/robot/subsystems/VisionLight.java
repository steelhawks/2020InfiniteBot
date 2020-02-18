/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.SensorSubsystem;

public class VisionLight extends SensorSubsystem {
  public final WPI_TalonSRX visionLightOne;

  public VisionLight() {
    this.visionLightOne = new WPI_TalonSRX(Robot.ROBOT_MAP.visionLightPortOne);
  }

  @Override
  public void enable() {
    this.visionLightOne.set(Robot.ROBOT_MAP.visionLightIntensity);
  }

  @Override
  public void disable() {
    this.visionLightOne.set(0.1);
  }

  @Override
  public void ping() {
  }

  @Override
  public boolean isAlive() {
    return this.visionLightOne.isAlive();
  }

  public void runLights()
  {
    try{
    
      if(Robot.DASHBOARDWS.cameraMode.equals("BALL"))
      {
        disable();
      }
      else
      {
        enable();
      }
    }
    catch(Exception e)
    {
      System.out.println("cannot turn on lights");
    }
  }
}