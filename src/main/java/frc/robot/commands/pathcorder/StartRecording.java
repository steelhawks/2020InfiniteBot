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
import frc.util.pathcorder.Recorder;
import edu.wpi.first.wpilibj.Joystick;


public class StartRecording implements Command 
{
  Joystick stick = Robot.COMMAND_LINKER.driveJoystick;
  Recorder record = new Recorder();

  public StartRecording() {}  
 
  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.DRIVETRAIN);
    return list;
  }

  @Override
  public void initialize() {
    Robot.DRIVETRAIN.count = 0;
    System.out.println("Starting recording...");
    Robot.RECORDER.startLog();
    Robot.RECORDER.isRecording = true;
  
  }

  @Override
  public void execute() 
  {
  }

  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
}

