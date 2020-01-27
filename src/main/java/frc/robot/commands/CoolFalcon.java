package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class CoolFalcon implements Command 
{

  public CoolFalcon() {}

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.SHOOTER);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
    System.out.print("Preparing to cool Falcons...");
    Robot.SHOOTER.coolFalcon();
  }
    
  @Override
  public boolean isFinished() 
  {
    return true;
  }

  @Override
  public void end(boolean interrupted) {}
}