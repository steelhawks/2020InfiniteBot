/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.shooter.ShooterSpool;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter.ShooterSpin;
import frc.robot.commands.storage.StorageMoveBalls;
import frc.robot.commands.storage.StorageReverseBalls;
import frc.robot.commands.storage.StorageStop;
import frc.robot.Robot;

public class VisionMiracleAlign extends CommandBase {
  public VisionMiracleAlign() {
    addRequirements(Robot.VISION);
    addRequirements(Robot.TURRET);
  }

  @Override
  public void initialize() {
    Robot.VISION_LIGHT.enable();
    Robot.STRIP_LIGHT.searchingTarget();
    Robot.VISION.toggleAlign();


    try {
      Thread.sleep(150);
    } catch (Exception e) {

    }

    Robot.DASHBOARDWS.getCameraMode();
    if (Robot.VISION.toggleAlign) 
    {
      System.out.println("passed");
      if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())) {
        System.out.println("initialized");
        Robot.VISION.reset();
        // (-1.27 * Math.pow(10, -6) * Math.pow(Robot.VISION.getDistance(), 4)) +
        // (1.11 * Math.pow(10, -3) * Math.pow(Robot.VISION.getDistance(), 3)) +
        // (-0.36 * Math.pow(Robot.VISION.getDistance(), 2)) +
        // (51.4 * Robot.VISION.getDistance()) +
        // -2798
        // );
        
        // if (Robot.DASHBOARDWS.cameraMode.equals("BALL")) {
        //   Robot.INTAKE.down();
        //   Robot.VISION_LIGHT.disable();
        // }
      } 
      else 
      {
        if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
          System.out.println("Already pressed, STOPPING SUBSYSTEMS");
        }
        System.out.println("No object to align to...");
      }
    }
  }

  @Override
  public void execute() {
    // if object is present then start alignment for objects
    if (Robot.VISION.toggleAlign && Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())) 
    {
      Robot.STRIP_LIGHT.aligningTarget();
      System.out.println("1");

      if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
        System.out.println("2");
        Robot.VISION.setAngle(Robot.VISION.getNTAngle());
        Robot.DRIVETRAIN.lowGear();
        Robot.VISION.align();
      }
      // } else if (Robot.DASHBOARDWS.cameraMode.equals("BALL")) {
      //   Robot.DRIVETRAIN.highGear();
      //   Robot.INTAKE.spinRoller(true);
      //   Robot.VISION.alignCurve();
      // } else if (Robot.DASHBOARDWS.cameraMode.equals("BAY")) {
      //   Robot.DRIVETRAIN.highGear();
      //   Robot.VISION.alignCurve();
      // }

    } 
    else 
    {
      Robot.STRIP_LIGHT.searchingTarget();
      System.out.println("No object to align to...1123");
    }
  }

  @Override
  public boolean isFinished() {
    boolean finished;
    if (!(Robot.VISION.toggleAlign)) {
      finished = true;
      Robot.VISION.isAligned = true;
      Robot.TURRET.goTo(Robot.ROBOT_MAP.turretQuarterPos);
    } else {
      finished = false;
    }
    return finished;

  }

  @Override
  public void end(boolean interrupted) {

    // if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
    //   // spool shootor, adjust velocity based on distance and start moving the balls
    //   if (!(Robot.VISION.toggleAlign)) {
    //     CommandScheduler.getInstance().schedule(
    //         new SequentialCommandGroup(new ParallelCommandGroup(new ShooterSpool(), new StorageReverseBalls()),
    //             new ParallelCommandGroup(new ShooterSpin(), new StorageMoveBalls())));
    //   } else {
    //     // if button was pressed, stop shooter and storage
    //     CommandScheduler.getInstance().schedule(new ParallelCommandGroup(new ShooterStop(), new StorageStop(), new TurretReturnToZero()));
    //   }

    // }
  }
}
