/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class VisionToggleAlign extends CommandBase {
  public VisionToggleAlign() {
    addRequirements(Robot.TURRET);
    addRequirements(Robot.VISION);
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    Robot.VISION.toggleAlign();
    
  }

  @Override
  public boolean isFinished() {
    boolean finished = true;
    return finished;

  }

  @Override
  public void end(boolean interrupted) {

  
  }
}