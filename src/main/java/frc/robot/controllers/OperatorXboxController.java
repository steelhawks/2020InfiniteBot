/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
import frc.robot.commands.climber.ClimberRollWinch;
import frc.robot.commands.climber.ClimberToggleSolenoid;
import frc.robot.commands.climber.ClimberStop;
import frc.robot.commands.climber.ClimberUnrollWinch;
import frc.robot.commands.intake.IntakeSpinRoller;
import frc.robot.commands.intake.IntakeStop;
import frc.robot.commands.intake.IntakeDown;
import frc.robot.commands.intake.IntakeToggleSolenoid;
import frc.robot.commands.shooter.ShooterSpin;
import frc.robot.commands.shooter.ShooterSpool;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.storage.StorageMoveBalls;
import frc.robot.commands.storage.StorageReverseBalls;
import frc.robot.commands.storage.StorageStop;


public class OperatorXboxController {

  private final Controller controller;

  public OperatorXboxController(Joystick joystickPort){
    this.controller = new Controller(joystickPort);
  }

  // replace Robot.BUTTON_MAP.button_name with corresponding buttons on joystick

  public void mapButtons() {

    // CLIMBER
    this.controller.mapButton(Robot.BUTTON_MAP.climberToggleSolenoidButton)
      .whenPressed(new SequentialCommandGroup(new IntakeDown(), new ClimberToggleSolenoid()));

    this.controller.mapButton(Robot.BUTTON_MAP.climberUnrollButton)
      .whenPressed(new ClimberUnrollWinch())
      .whenReleased(new ClimberStop());

    this.controller.mapButton(Robot.BUTTON_MAP.climberRollWinchButton)
      .whenPressed(new ClimberRollWinch())
      .whenReleased(new ClimberStop());
    
    this.controller.mapButton(Robot.BUTTON_MAP.shooterSpinForwardButton)
      .whenPressed(new SequentialCommandGroup( new ShooterSpool(),new ShooterSpin()))
      .whenReleased(new ShooterStop());

    this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinRollerForwardButton)
      .whenPressed(new IntakeSpinRoller())
      .whenReleased(new IntakeStop());

    this.controller.mapButton(Robot.BUTTON_MAP.intakeToggleSolenoidButton)
      .whenPressed(new IntakeToggleSolenoid());

    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsForwardButton)
      .whenPressed(new StorageMoveBalls())
      .whenReleased(new StorageStop());

    this.controller.mapButton(Robot.BUTTON_MAP.StorageMoveBallsReverseButton)
      .whenPressed(new StorageReverseBalls())
      .whenReleased(new StorageStop());

  }
}
