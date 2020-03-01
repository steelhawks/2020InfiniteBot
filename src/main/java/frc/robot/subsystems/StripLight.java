/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.Robot;

import frc.util.subsystems.SensorSubsystem;

public class StripLight extends SensorSubsystem {
  public final Spark stripLightOne;

  public StripLight() {
    this.stripLightOne = new Spark(Robot.ROBOT_MAP.stripLightPortOne);
  }

  @Override
  public void enable() {
    this.stripLightOne.set(0.55); // Sinelon, Color 1 and 2
  }

  @Override
  public void disable() {
    this.stripLightOne.set(-0.59); // Fire, Medium
  }

  public void autonomousDefault(DriverStation.Alliance alliance) {
    if (alliance.equals(DriverStation.Alliance.Red)) {
      this.stripLightOne.set(-0.31); // Light Chase, Red
    } else if (alliance.equals(DriverStation.Alliance.Blue)) {
      this.stripLightOne.set(-0.29); // Light Chase, Blue
    } else {
      this.stripLightOne.set(-0.27); // Light Chase, Gray
    }
  }

  public void teleopDefault(DriverStation.Alliance alliance) {
    if (alliance.equals(DriverStation.Alliance.Red)) {
      this.stripLightOne.set(-0.25); // Heartbeat, Red
    } else if (alliance.equals(DriverStation.Alliance.Blue)) {
      this.stripLightOne.set(-0.23); // Heartbeat, Blue
    } else {
      this.stripLightOne.set(-0.19); // Heartbeat, Gray
    }
  }

  public void endgameDeafult(DriverStation.Alliance alliance) {
    if (alliance.equals(DriverStation.Alliance.Red)) {
      this.stripLightOne.set(-0.17); // Breath, Red
    } else if (alliance.equals(DriverStation.Alliance.Blue)) {
      this.stripLightOne.set(-0.15); // Breath, Blue
    } else {
      this.stripLightOne.set(-0.13); // Breath, Gray
    }
  }

  public void searchingTarget() {
    this.stripLightOne.set(-0.33); // Larson Scanner, Gray
  }

  public void aligningTarget() {
    this.stripLightOne.set(-0.21); // Heartbeat, White
  }

  public void aligned() {
    this.stripLightOne.set(-0.07); // Strobe, Gold
  }

  public void shooting() {
    this.stripLightOne.set(-0.71); //Sinelon, Forest Palette
  }

  @Override
  public void ping() {
  }

  @Override
  public boolean isAlive() {
    return this.stripLightOne.isAlive();
  }
}