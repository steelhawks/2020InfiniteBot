/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.CoolFalcon;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {


  public static final RobotMap ROBOTMAP = new RobotMap();
  public static final Climber CLIMBER = new Climber();
  public static final Shooter SHOOTER = new Shooter();
  public static final CommandLinker COMMAND_LINKER = new CommandLinker();

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    SHOOTER.shoot(COMMAND_LINKER.DRIVE_JOYSTICK.getY());
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public void shouldCoolFalcons()
  {
    int lastTimeCooled = -1;

    boolean shouldCool = !(lastTimeCooled == (int)Timer.getFPGATimestamp()) && ((int)(Timer.getFPGATimestamp() % 5) == 0);
    System.out.println("Setting boolean: " + shouldCool);
    if(shouldCool)
    {
      CommandScheduler.getInstance().schedule(true, new CoolFalcon());
      lastTimeCooled = (int)Timer.getFPGATimestamp();
      System.out.println("Last Time Cooled " + lastTimeCooled);
      System.out.println("Current Time " + (int)Timer.getFPGATimestamp());
      System.out.println("Status" + (!(lastTimeCooled == (int)Timer.getFPGATimestamp()) && ((int)(Timer.getFPGATimestamp() % 5) == 0)));
    }
  }
}
