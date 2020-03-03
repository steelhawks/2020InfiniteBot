/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.StripLight;
import frc.robot.subsystems.Turret;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.VisionLight;
import frc.robot.subsystems.VisionMount;

import frc.util.pathcorder.Follower;
import frc.util.pathcorder.Recorder;
import frc.util.websocket.DashboardWS;
import frc.util.websocket.TrackingWS;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static final RobotMap ROBOT_MAP = new RobotMap();
  public static final ButtonMap BUTTON_MAP = new ButtonMap();
  public static final DashboardWS DASHBOARDWS = new DashboardWS();
  public static final TrackingWS TRACKINGWS = new TrackingWS();
  public static final Recorder RECORDER = new Recorder();
  public static final Follower FOLLOWER = new Follower();
  public static final Climber CLIMBER = new Climber();
  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final Intake INTAKE = new Intake();
  public static final Shooter SHOOTER = new Shooter();
  public static final Storage STORAGE = new Storage();
  public static final Turret TURRET = new Turret();
  public static final Vision VISION = new Vision();
  public static final VisionLight VISION_LIGHT = new VisionLight();
  public static final VisionMount VISION_MOUNT = new VisionMount();
  public static final StripLight STRIP_LIGHT = new StripLight();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker();

  @Override
  public void robotInit() {
    Robot.COMMAND_LINKER.configureRegisteredSubsystems();
    Robot.COMMAND_LINKER.configurePeriodicBindings();
    Robot.COMMAND_LINKER.configureButtonBindings();

    Robot.DRIVETRAIN.lowGear();

    Robot.DASHBOARDWS.connect();
    Robot.TRACKINGWS.connect();
    Robot.DASHBOARDWS.baseConfig();

    Robot.VISION.setXPosLeftLimit(315.0);
    Robot.VISION.setXPosRightLimit(325.0);

    Robot.FOLLOWER.importPath(ROBOT_MAP.paths);

    Robot.STRIP_LIGHT.enable();
  }

  @Override
  public void robotPeriodic() {
    String driverMessage = "Welcome " + ROBOT_MAP.driver + " & " + ROBOT_MAP.operator + "! Are you ready to play in "
        + DriverStation.getInstance().getMatchType() + " " + DriverStation.getInstance().getMatchNumber() + " on the "
        + DriverStation.getInstance().getAlliance() + " in position " + DriverStation.getInstance().getLocation() + "?";
    SmartDashboard.putString("Welcome Message", driverMessage);

    if (DriverStation.getInstance().getMatchTime() <= 0) {
      SmartDashboard.putString("Match Time", "Match is over or has not started yet...");
    } else {
      String matchTime = DriverStation.getInstance().getMatchTime() / 60 + ":"
          + DriverStation.getInstance().getMatchTime() % 60;
      SmartDashboard.putString("Match Time", matchTime);
    }
  }

  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().disable();
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void autonomousInit() {
    Robot.DRIVETRAIN.lowGear();
    Robot.FOLLOWER.index = 0;
    Robot.STRIP_LIGHT.autonomousDefault(DriverStation.getInstance().getAlliance());
    CommandScheduler.getInstance().schedule(ROBOT_MAP.autonCommands);
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().enable();
    Robot.DRIVETRAIN.lowGear();
    if (DriverStation.getInstance().getMatchTime() <= 30) {
      Robot.STRIP_LIGHT.endgameDeafult(DriverStation.getInstance().getAlliance());
    } else {
      Robot.STRIP_LIGHT.teleopDefault(DriverStation.getInstance().getAlliance());
    }
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
