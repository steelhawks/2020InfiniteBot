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
import java.util.Set;
import java.util.HashSet;

public class ShooterSpoolVision implements Command
{
    public ShooterSpoolVision() {}

    @Override
    public Set<Subsystem> getRequirements() 
    {
        Set<Subsystem> list = new HashSet<Subsystem>();
        list.add(Robot.SHOOTER);
        list.add(Robot.VISION);
        return list;
    }

    @Override
    public void initialize()
    {
        
    }

    @Override
    public void execute()
    {
      if(Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData())){
          Robot.SHOOTER.visionSpool();
      }
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

    @Override
    public void end(boolean interrupted)
    {
        Robot.SHOOTER.stop();
    }
}