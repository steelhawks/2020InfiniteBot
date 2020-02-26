package frc.util.pathcorder;
import java.util.ArrayList;
import frc.util.pathcorder.DashboardPath;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathList
{
  public ArrayList<DashboardPath> existingPaths;

  public PathList(){
    existingPaths = new ArrayList<DashboardPath>(0);
  }

  public void setDefaults(){
    DashboardPath path1 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(

                            ));
    DashboardPath path2 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(

                            ));   
    DashboardPath path3 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(

                            ));
    DashboardPath path4 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(

                            ));                                                               
    DashboardPath path5 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(
  
                            ));
    DashboardPath path6 = new DashboardPath("Red 1"
                          , new SequentialCommandGroup(
  
                            ));


    existingPaths.add(path1);
    existingPaths.add(path2);
    existingPaths.add(path3);
    existingPaths.add(path4);
    existingPaths.add(path5);
    existingPaths.add(path6);

  }
}