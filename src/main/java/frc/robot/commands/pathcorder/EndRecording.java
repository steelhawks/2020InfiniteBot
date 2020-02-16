/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.pathcorder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import java.util.HashSet;
import java.util.Set;

public class EndRecording implements Command 
{
  public EndRecording() {}
 
  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.DRIVETRAIN);
    return list;
  }

  @Override
  public void initialize() {
    System.out.println("Ending recording...");
    Robot.RECORDER.isRecording = false;
  }
  @Override
  public void execute() {}

  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) 
  {
    Robot.RECORDER.exportPath(Robot.ROBOT_MAP.pathName);
    Robot.RECORDER.joystickValues.clear();
    
  }
}


