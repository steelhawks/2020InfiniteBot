package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class ClimberDown implements Command 
{

  public ClimberDown() {}

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.CLIMBER);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
    Robot.CLIMBER.climberDownButton();
  }
    
  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) 
  {
    if(interrupted)
    {
      Robot.CLIMBER.stopClimber();
    }
  }
}