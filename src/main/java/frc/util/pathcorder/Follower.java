package frc.util.pathcorder;

import edu.wpi.first.wpilibj.Filesystem;
//import edu.wpi.first.wpilibj.Joystick;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

//import java.sql.Time;

import java.util.ArrayList;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.util.pathcorder.Recorder;
import frc.util.pathcorder.AutonPath;

public class Follower
{

  public int index = 0;
  public boolean shouldDouble = false;
  public boolean isFinished = false;
  private ArrayList<AutonPath> AutonPaths = new ArrayList<AutonPath>(0);
  private int pathIndex = 0;


  public void follow()
  {
    AutonPath currentPath = AutonPaths.get(pathIndex);
    if (index < currentPath.joystickYValues.size())
    {
      System.out.println(index);
      Robot.DRIVETRAIN.arcadeDrive(currentPath.joystickYValues.get(index), currentPath.joystickRotationValues.get(index));
      index++;
      if (shouldDouble == true)
      {
        shouldDouble = false;
        index++;
      }
      else
      {
        shouldDouble = true;
      }

      isFinished = false;
      //Robot.DRIVETRAIN.enc_right.setPosition(this.encoderRightValues.get(index));
      //Robot.DRIVETRAIN.enc_left.setPosition(this.encoderLeftValues.get(index));
    }
    else
    {
      Robot.DRIVETRAIN.arcadeDrive(0, 0);
      System.out.println(index);
      pathIndex++;
      isFinished = true;
    }
  }
 

  public void importPath(String[] paths)
  {
    try 
    {
      //insert path to csv
      for(String pathName: paths){
        System.out.println("PATH NAME" + pathName);
        Path path = Paths.get(pathName);
        Path fileName = path.getFileName();
        File TBR = fileName.toFile();
        String file = fileName.toString();
        System.out.println("this is name: " + file);
        bufferedReader(TBR); 
      }
      

      // throw new IOException();
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
      System.out.println("Failed to load " + Robot.ROBOT_MAP.paths + "...");
    }
  }

  public void bufferedReader(File fileName)
  {
   try
   {

      String filePathName = Filesystem.getDeployDirectory().toString() + "/" + fileName;
      System.out.println(filePathName);
      File exFile = new File(filePathName);
      System.out.println(exFile);
      FileReader in = new FileReader(exFile); 
      System.out.println("made filereader");
      // System.out.println("File reader" + in.ready());
      BufferedReader br = new BufferedReader(in); 
      System.out.println("made bufferedreader");
      // System.out.println("buffered reader" + br.ready());
      br.readLine();
      br.readLine();
      AutonPath newPath = new AutonPath();
      String line = null;
      while ((line = br.readLine()) != null)
      {
        String[] joystickValue = line.split(",");
        double valueOne = Double.parseDouble(joystickValue[0]);
        double valueTwo = Double.parseDouble(joystickValue[1]);
        if(valueOne > 1 || valueOne < -1 || valueTwo > 1 || valueTwo < -1){
          newPath.encoderRightValues.add(valueOne);
          newPath.encoderLeftValues.add(valueTwo);
        }
        else{
          newPath.joystickYValues.add(valueOne); 
          newPath.joystickRotationValues.add(valueTwo); 
        }

      }
      AutonPaths.add(newPath);
      br.close();
      // // br.read(); 
      // // br.ready(); 
    }
    catch (Exception e)
    {
      System.out.println("Could not find file");
    } 
  }
  
    
  // String csvFile = " ";
  // BufferedReader br = null;
  // String line = "";
  // String cvsSplitBy = ",";


  // public void get
  // {
  //   if(joystickValues.equals("joystick"))
  //   {
  //     System.out.println("")
  // }
}
