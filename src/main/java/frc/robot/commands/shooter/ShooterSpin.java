/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class ShooterSpin implements Command {
  public ShooterSpin() {
  }

  @Override
  public Set<Subsystem> getRequirements() {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.SHOOTER);
    return list;
  }

  @Override
  public void initialize() {
    Robot.STRIP_LIGHT.shooting();
  }

  @Override
  public void execute() {
    if(Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())){
      // Robot.SHOOTER.shoot((0.0119*Math.pow(Robot.VISION.getDistance(), 2)) + (13.8 * Robot.VISION.getDistance()) + 17484);
      Robot.SHOOTER.shoot( 
        (-2.92 * Math.pow(10, -10) * Math.pow(Robot.VISION.getDistance(), 4)) + 
        (4.7 * Math.pow(10, -7) * Math.pow(Robot.VISION.getDistance(), 3)) +
        (2.7 * Math.pow(10, -4) * Math.pow(Robot.VISION.getDistance(), 2)) +
        (0.0651 * Robot.VISION.getDistance()) +
        -4.86);
    }
    else
    {
      Robot.SHOOTER.shoot(0.75);
    }
  }

  @Override
  public boolean isFinished() {
    System.out.println("finishing");
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      System.out.println("ending");
      // Robot.SHOOTER.stop();
    }
  }
}