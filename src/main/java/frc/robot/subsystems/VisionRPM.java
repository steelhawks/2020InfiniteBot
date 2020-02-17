/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.Robot;
import frc.util.subsystems.MechanicalSubsystem;

public class VisionRPM extends MechanicalSubsystem {


  public VisionRPM() {
  }

  public void changeRPM(){
    Robot.ROBOT_MAP.shooterMaxRPM = (int)(Robot.VISION.getDistance() / Robot.ROBOT_MAP.distanceToRpm);
  }

  @Override
  public void ping() {
  }

  @Override
  public boolean isAlive() {
    return true;
  }

  public void smartDashboard() {}
  
  public boolean stop(){
    return true;
  }

  

}