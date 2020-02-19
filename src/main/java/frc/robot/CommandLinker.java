/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.Joystick;

import frc.robot.commands.drivetrain.DrivetrainDefault;
import frc.robot.controllers.Driver3DProController;
import frc.robot.controllers.OperatorXboxController;

import frc.util.Gamepad;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class CommandLinker {

  public final Joystick driveJoystick = new Joystick(Robot.BUTTON_MAP.joystickOnePort);
  public final Gamepad operatorGamepad = new Gamepad(Robot.BUTTON_MAP.gamepadOnePort);
  private final Driver3DProController driverController = new Driver3DProController(driveJoystick);
  private final OperatorXboxController xboxController = new OperatorXboxController(operatorGamepad);

  public CommandLinker() {
  }

  public void configureRegisteredSubsystems() {
    CommandScheduler.getInstance().registerSubsystem(Robot.CLIMBER);
    CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
    CommandScheduler.getInstance().registerSubsystem(Robot.INTAKE);
    CommandScheduler.getInstance().registerSubsystem(Robot.SHOOTER);
    CommandScheduler.getInstance().registerSubsystem(Robot.STORAGE);
    CommandScheduler.getInstance().registerSubsystem(Robot.VISION);
    CommandScheduler.getInstance().registerSubsystem(Robot.VISION_LIGHT);
  }

  public void configurePeriodicBindings() {
    CommandScheduler.getInstance().setDefaultCommand(Robot.DRIVETRAIN, new DrivetrainDefault());
  }

  public void configureButtonBindings() {
    driverController.mapButtons();
    xboxController.mapButtons();
  }
}
