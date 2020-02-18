/*----------------------------------------------------------------------------*/
/*                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
import frc.robot.commands.storage.StorageStop;
import frc.robot.commands.vision.Align;
import frc.robot.commands.vision.Connect;
import frc.robot.commands.vision.RequestBall;
import frc.robot.commands.vision.RequestBay;
import frc.robot.commands.vision.RequestPort;
import frc.util.Gamepad;


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
      .whenPressed(new SequentialCommandGroup( new ShooterSpool()
      , new ParallelCommandGroup( new ShooterSpin(), new StorageMoveBalls())))
      .whenReleased(new ParallelCommandGroup( new ShooterStop(), new StorageStop()));

    this.controller.mapButton(Robot.BUTTON_MAP.intakeSpinRollerForwardButton)
      .whenPressed(new IntakeSpinRoller())
      .whenReleased(new IntakeStop());

    this.controller.mapButton(Robot.BUTTON_MAP.intakeToggleSolenoidButton)
      .whenPressed(new IntakeToggleSolenoid());

    this.controller.mapButton(Robot.BUTTON_MAP.storageMoveBallsForwardButton)
      .whenPressed(new StorageMoveBalls())
      .whenReleased(new StorageStop());

  }
}
