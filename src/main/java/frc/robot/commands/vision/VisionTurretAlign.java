/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class VisionTurretAlign extends CommandBase {
  public VisionTurretAlign() {
    addRequirements(Robot.SHOOTER);
    addRequirements(Robot.STORAGE);
    addRequirements(Robot.TURRET);
    addRequirements(Robot.VISION);
  }

  @Override
  public void initialize() {
    Robot.VISION_LIGHT.enable();
    Robot.STRIP_LIGHT.searchingTarget();
      if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())) {
        System.out.println("initialized");
        Robot.VISION.setXPosOffset(-5);
        // (-1.27 * Math.pow(10, -6) * Math.pow(Robot.VISION.getDistance(), 4)) +
        // (1.11 * Math.pow(10, -3) * Math.pow(Robot.VISION.getDistance(), 3)) +
        // (-0.36 * Math.pow(Robot.VISION.getDistance(), 2)) +
        // (51.4 * Robot.VISION.getDistance()) +
        // -2798
        // );
      } 
      else {
        System.out.println("No object to align to...");
      }
    }

  @Override
  public void execute() {
    // if object is present then start alignment for objects
    Robot.VISION.reset();
    if (Robot.VISION.toggleAlign && Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())) {
      Robot.STRIP_LIGHT.aligningTarget();
      Robot.VISION.setAngle(Robot.VISION.getNTAngle());
      System.out.println("1");
      if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
        Robot.VISION.align();
      }
    } else {
      Robot.STRIP_LIGHT.searchingTarget();
      System.out.println("No object to align to...1123");
    }
  }

  @Override
  public boolean isFinished() {
    boolean finished;
    if (!Robot.VISION.toggleAlign) {
      finished = true;
    } else {
      finished = false;
    }
    return finished;

  }

  @Override
  public void end(boolean interrupted) {

  
  }
}
