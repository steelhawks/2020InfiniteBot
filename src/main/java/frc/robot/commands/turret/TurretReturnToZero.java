/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class TurretReturnToZero extends CommandBase {
  public TurretReturnToZero() {
    addRequirements(Robot.TURRET);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.TURRET.goTo(Robot.ROBOT_MAP.turretZeroPos);
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted)
    {
      Robot.TURRET.stop();
    }
  }

}