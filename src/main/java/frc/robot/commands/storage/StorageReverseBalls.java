/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.storage;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class StorageReverseBalls implements Command {
  public StorageReverseBalls() {
  }

  @Override
  public Set<Subsystem> getRequirements() {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.STORAGE);
    return list;
  }

  @Override
  public void initialize() {
    Robot.STORAGE.isFullyReversed = false;
  }

  @Override
  public void execute() {
    Robot.STORAGE.moveBalls(false);
  }

  @Override
  public boolean isFinished() {
    return Robot.STORAGE.isFullyReversed;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.STORAGE.stop();
    }
  }
}