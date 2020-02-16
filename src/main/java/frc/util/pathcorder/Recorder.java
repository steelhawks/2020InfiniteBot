package frc.util.pathcorder;


//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;


import com.revrobotics.CANEncoder;
//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//import javax.sound.sampled.Line;

//import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import frc.util.pathcorder.Follower;
import frc.util.pathcorder.*;
import frc.util.CsvLogger;
import frc.robot.Robot;

public class Recorder
{
  public ArrayList<JoystickRecorder> joystickValues;
  public ArrayList<Encorder> encoderValues;
  public boolean isRecording;
  public boolean usingRecording;
  public int index;
  public String [] name = {"joystick value y","joystick twist","right encoder", "left encoder"};
  public String [] value= {"","","rpm","rpm"};
  public String csvList = "";
  CsvLogger logger = new CsvLogger();


  public Recorder()
  {
    this.joystickValues = new ArrayList<JoystickRecorder>();
    this.encoderValues = new ArrayList<Encorder>();
    this.isRecording = false;
    this.usingRecording = false;
    this.index = 0;
    
  }
  public void startLog()
  {
    logger.init(name, value);
 
  }

  public void recordJoystick(JoystickRecorder stick)
  {
    logger.writeData(stick.joystickY, stick.joystickTwist, stick.count); 
  }
 
  public void recordEncoderValues(Encorder enc)
  {
     logger.writeData(enc.rightEncoder,enc.leftEncoder);
  }

  public void exportPath(String pathName) 
  {
    // SmartDashboard.putString("CSV String", csvList);
    try 
    {   
      Path path = Paths.get(pathName);
      Path fileName = path.getFileName();
      System.out.println("FileName: " + fileName.toString());
      for(int i = 0; i < 25; i++){
        logger.writeData(0.0 , 0.0, 0);
      }
      logger.forceSync();
      logger.close();

    } 
    catch (Exception e) 
    {
      e.printStackTrace();
      System.out.println("Failed to load " + Robot.ROBOT_MAP.paths + "...");
    }
  }
 
}







