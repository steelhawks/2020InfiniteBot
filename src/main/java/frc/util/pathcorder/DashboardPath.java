package frc.util.pathcorder;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DashboardPath {
  public String pathName;
  public SequentialCommandGroup commands;

  public DashboardPath(String pathName, SequentialCommandGroup commands) {
    this.pathName = pathName;
    this.commands = commands;
  }
}