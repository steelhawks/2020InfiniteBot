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

public class VisionShoot extends CommandBase {
  public VisionShoot() {
    addRequirements(Robot.SHOOTER);
    addRequirements(Robot.STORAGE);
    addRequirements(Robot.TURRET);
    addRequirements(Robot.VISION);
  }

  @Override
  public void initialize() {
    Robot.VISION_LIGHT.enable();
    Robot.STRIP_LIGHT.searchingTarget();
    Robot.VISION.toggleAlign();
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
    if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())) {
      Robot.STRIP_LIGHT.aligningTarget();
      Robot.VISION.setAngle(Robot.VISION.getNTAngle());
      System.out.println("1");
      if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
        Robot.VISION.align();
      }
    } 
    else {
      Robot.STRIP_LIGHT.searchingTarget();
      System.out.println("No object to align to...1123");
    }
  }

  @Override
  public boolean isFinished() {
    boolean finished;
    if (Robot.VISION.isAligned()) {
      finished = true;
    } else {
      finished = false;
    }
    return finished;

  }

  @Override
  public void end(boolean interrupted) {
    if (Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")) {
      // spool shootor, adjust velocity based on distance and start moving the balls
      if(!Robot.VISION.stopShooting){
        CommandScheduler.getInstance().schedule(
            new SequentialCommandGroup(new ParallelCommandGroup(new ShooterSpool(), new StorageReverseBalls()),
                new ParallelCommandGroup(new ShooterSpin(), new StorageMoveBalls())));
        Robot.VISION.stopShooting = true;
      }
      else {
        CommandScheduler.getInstance().schedule(new ParallelCommandGroup(new ShooterStop(), new StorageStop()));
        Robot.VISION.stopShooting = false;
        Robot.VISION.toggleAlign();
      }
      
      
      // if button was pressed, stop shooter and storage
      

    }
  }
}
