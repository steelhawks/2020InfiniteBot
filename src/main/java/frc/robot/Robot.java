/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.drivetrain.DrivetrainCoolFalcons;
import frc.robot.commands.intake.IntakeVomit;
import frc.robot.commands.shooter.ShooterVomit;
import frc.robot.commands.storage.StorageVomit;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Funnel;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Storage;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.VisionLight;
import frc.robot.subsystems.VisionMount;
import frc.robot.subsystems.VisionRPM;
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
  public static final DashboardWS DASHBOARDWS = new DashboardWS();
  public static final TrackingWS TRACKINGWS = new TrackingWS();
  public static final Recorder RECORDER = new Recorder();
  public static final Follower FOLLOWER = new Follower();
  public static final Climber CLIMBER = new Climber();
  public static final Shooter SHOOTER = new Shooter();
  public static final Storage STORAGE = new Storage();
  public static final Drivetrain DRIVETRAIN = new Drivetrain();
  public static final Funnel FUNNEL = new Funnel();
  public static final Intake INTAKE = new Intake();
  public static final Vision VISION = new Vision();
  public static final VisionLight VISION_LIGHT = new VisionLight();
  public static final VisionMount VISION_MOUNT = new VisionMount();
  public static final VisionRPM VISION_RPM = new VisionRPM();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker();
  public NetworkTableEntry startingPosition;
  public NetworkTableEntry autonPath;

  @Override
  public void robotInit() {
    COMMAND_LINKER.configureRegisteredSubsystems();
    COMMAND_LINKER.configurePeriodicBindings();
    COMMAND_LINKER.configureButtonBindings();
    //CLIMBER.retractSolenoid();
    DRIVETRAIN.lowGear();
    INTAKE.intakeMotorOne.stopMotor();
    
    INTAKE.up();
    //DASHBOARDWS.connect();
    //DASHBOARDWS.baseConfig();
    //TRACKINGWS.connect();
    FOLLOWER.importPath(ROBOT_MAP.paths);
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

    // this.startingPosition = Shuffleboard.getTab("Auton").add("Starting Position", 3).withPosition(0, 0).getEntry();
    // this.autonPath = Shuffleboard.getTab("Auton").add("Path", 3).withPosition(0, 0).getEntry();

    // SmartDashboard.putString("Auton", "Position: " + this.startingPosition.toString() + "\nDeploying Path" + this.autonPath.toString());

    // Vision periodic
    // VISION_LIGHT.runLights();
    //DASHBOARDWS.reconnect();
    //TRACKINGWS.reconnect();

    

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
    CommandScheduler.getInstance().schedule(ROBOT_MAP.autonCommands);
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    CommandScheduler.getInstance().enable();
    DRIVETRAIN.lowGear();
    INTAKE.down();
  }

  @Override
  public void teleopPeriodic() {
    CommandScheduler.getInstance().run();
    // if (DriverStation.getInstance().getMatchTime() < 10) {
    //   CommandScheduler.getInstance().schedule(
    //       new ParallelCommandGroup(new IntakeVomit(), new ShooterVomit(), new StorageVomit()));
    // }
  }

  public void shouldCoolFalcons() {
    int lastTimeCooled = -1;

    boolean shouldCool = !(lastTimeCooled == (int) Timer.getFPGATimestamp())
        && ((int) (Timer.getFPGATimestamp() % 5) == 0);
    System.out.println("Setting boolean: " + shouldCool);
    if (shouldCool) {
      CommandScheduler.getInstance().schedule(true, new DrivetrainCoolFalcons());
      lastTimeCooled = (int) Timer.getFPGATimestamp();
    }
  }
}
