package frc.util.pathcorder;
import java.util.ArrayList;
import frc.util.pathcorder.DashboardPath;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class PathList
{
  public ArrayList<DashboardPath> existingPaths;

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

  public PathList(){
    this.existingPaths = new ArrayList<DashboardPath>(0);
  }

  public void setDefaults(){
    // DashboardPath path1 
    // existingPaths.add(path1);
    this.existingPaths.add(path1);
    this.existingPaths.add(path2);
    this.existingPaths.add(path3);
    this.existingPaths.add(path4);
    this.existingPaths.add(path5);
    this.existingPaths.add(path6);
  }
}
