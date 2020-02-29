/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class IntakeSpinRollerReverse implements Command {
  public IntakeSpinRollerReverse() {
  }

  @Override
  public Set<Subsystem> getRequirements() {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.INTAKE);
    return list;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.INTAKE.spinRoller(-Robot.ROBOT_MAP.intakeSpeed);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted)
    {
      Robot.INTAKE.stop();
    }
  }
}