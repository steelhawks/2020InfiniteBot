/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class VisionConnect extends CommandBase {
  public VisionConnect() {
    addRequirements(Robot.VISION);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    try {
      for(int i = 0; i < 4; i++){
        Robot.TRACKINGWS = Robot.TRACKINGWS.reconnect();
        Robot.DASHBOARDWS = Robot.DASHBOARDWS.reconnect();
      }
    } catch (Exception e) {
      System.out.println("idk man i cant connect");

    }

  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }

}