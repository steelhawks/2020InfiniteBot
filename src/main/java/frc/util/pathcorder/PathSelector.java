// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.util.pathcorder;
// import frc.robot.Robot;
// import frc.util.pathcorder.PathList;;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import java.util.ArrayList;
// import java.io.File;



// import edu.wpi.first.wpilibj.Filesystem;

// public class PathSelector {

//   SendableChooser chooser;
//   SendableChooser pathCreater;
//   File[] autonFiles;

//   public PathSelector(){
//     SendableChooser chooser = new SendableChooser<>();
//     // creates multiple choice selector
//     String deployDirectory = Filesystem.getDeployDirectory().toString();
//     autonFiles = new File(deployDirectory).listFiles();
//     // creates select button
//   }

//   public void presetPaths(){
//     PathList pathList = new PathList();
//     ArrayList<DashboardPath> paths = pathList.existingPaths;
//     for(int i = 0; i < paths.size(); i++){
//       String optionName = paths.get(i).pathName;
//       SequentialCommandGroup optionCommands = paths.get(i).commands;
//       chooser.addOption(optionName, optionCommands);
//     }
//     SmartDashboard.putData("Preset Path", chooser);
//   }

//   public void loadPresetPath(){
//     SequentialCommandGroup choice = (SequentialCommandGroup)chooser.getSelected();
//     Robot.ROBOT_MAP.autonCommands = choice;
//   }

//   public void customPath(){
//     for(int i = 0; i < autonFiles.length; i++){
//       String fileName = autonFiles[i].toString();
//       pathCreater.addOption(fileName, fileName);
//     }
//     pathCreater.addOption("Align", "ALIGN");
//     pathCreater.addOption("Port Mode", "PORT");
//     pathCreater.addOption("Bay Mode", "BAY");
//     pathCreater.addOption("Ball Mode", "BALL");
//     SmartDashboard.putData("Custom Path Selector", pathCreater);

//     // create select button and loader
//   }


// }