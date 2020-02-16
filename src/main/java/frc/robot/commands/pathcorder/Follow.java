package frc.robot.commands.pathcorder;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import java.util.HashSet;
import java.util.Set;

public class Follow implements Command 
{
  public Follow() {}
 

  @Override
  public Set<Subsystem> getRequirements() 
  {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.DRIVETRAIN);
    return list;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() 
  {
      Robot.FOLLOWER.follow();    
  }

  @Override
  public boolean isFinished() 
  {
    System.out.println(Robot.FOLLOWER.isFinished);
    return Robot.FOLLOWER.isFinished;
  }

  @Override
  public void end(boolean interrupted) {}
}