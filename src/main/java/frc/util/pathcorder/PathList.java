package frc.util.pathcorder;
import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathList
{
  public ArrayList<DashboardPath> existingPaths;

  public PathList(){
    existingPaths = new ArrayList<DashboardPath>(0);
  }


}