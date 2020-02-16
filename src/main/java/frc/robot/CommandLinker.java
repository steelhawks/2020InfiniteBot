/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.climber.ClimberRollWinch;
import frc.robot.commands.climber.ClimberToggleSolenoid;
import frc.robot.commands.climber.ClimberStop;
import frc.robot.commands.climber.ClimberUnrollWinch;
import frc.robot.commands.drivetrain.DrivetrainCoolFalcons;
import frc.robot.commands.drivetrain.DrivetrainDefault;
import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
import frc.robot.commands.drivetrain.DrivetrainShiftGear;
import frc.robot.commands.funnel.FunnelMoveBalls;
import frc.robot.commands.funnel.FunnelVomit;
import frc.robot.commands.intake.IntakeSpinRoller;
import frc.robot.commands.intake.IntakeStop;
import frc.robot.commands.intake.IntakeToggleSolenoid;
import frc.robot.commands.intake.IntakeVomit;
import frc.robot.commands.pathcorder.EndRecording;
import frc.robot.commands.pathcorder.StartRecording;
import frc.robot.commands.shooter.ShooterSpin;
import frc.robot.commands.shooter.ShooterSpool;
import frc.robot.commands.shooter.ShooterVomit;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.storage.StorageMoveBalls;
import frc.robot.commands.storage.StorageVomit;
import frc.robot.commands.storage.StorageStop;
import frc.robot.commands.vision.Align;
import frc.robot.commands.vision.Connect;
import frc.robot.commands.vision.RequestBall;
import frc.robot.commands.vision.RequestBay;
import frc.robot.commands.vision.RequestPort;
import frc.util.Gamepad;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */

public class CommandLinker {

  public final Joystick driveJoystick = new Joystick(Robot.ROBOT_MAP.joystickOnePort);
  public final Gamepad operatorGamepad = new Gamepad(Robot.ROBOT_MAP.gamepadOnePort);

  public CommandLinker() {
  }

  public void configureRegisteredSubsystems() {
    CommandScheduler.getInstance().registerSubsystem(Robot.CLIMBER);
    CommandScheduler.getInstance().registerSubsystem(Robot.DRIVETRAIN);
    //CommandScheduler.getInstance().registerSubsystem(Robot.FUNNEL);
    CommandScheduler.getInstance().registerSubsystem(Robot.INTAKE);
    CommandScheduler.getInstance().registerSubsystem(Robot.SHOOTER);
    CommandScheduler.getInstance().registerSubsystem(Robot.STORAGE);
    CommandScheduler.getInstance().registerSubsystem(Robot.VISION);
    CommandScheduler.getInstance().registerSubsystem(Robot.VISION_LIGHT);
  }

  public void configurePeriodicBindings() {
    // DRIVETRAIN

    CommandScheduler.getInstance().setDefaultCommand(Robot.DRIVETRAIN, new DrivetrainDefault());
  }

  public void configureButtonBindings() {
    // CLIMBER
    Button climberToggleSolenoidButton = new JoystickButton(this.operatorGamepad,
        Robot.ROBOT_MAP.climberToggleSolenoidButton);
    climberToggleSolenoidButton.whenPressed(new ClimberToggleSolenoid());

    Button climberUnrollButton = new JoystickButton(this.operatorGamepad, Robot.ROBOT_MAP.climberUnrollButton);
    climberUnrollButton.whenPressed(new ClimberUnrollWinch());
    climberUnrollButton.whenReleased(new ClimberStop());

    Button climberRollWinchButton = new JoystickButton(this.operatorGamepad, Robot.ROBOT_MAP.climberRollWinchButton);
    climberRollWinchButton.whenPressed(new ClimberRollWinch());
    climberRollWinchButton.whenReleased(new ClimberStop());
    // DRIVETRAIN
    Button drivetrainShiftButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.drivetrainShiftButton);
    drivetrainShiftButton.whenPressed(new DrivetrainShiftGear());

    Button drivetrainReverDirectionButton = new JoystickButton(this.driveJoystick,
        Robot.ROBOT_MAP.drivetrainReverseDirectionButton);
    drivetrainReverDirectionButton.whenPressed(new DrivetrainReverseDirection());

    Button drivetrainCoolFalconsButton = new JoystickButton(this.driveJoystick,
        Robot.ROBOT_MAP.drivetrainCoolFalconsButton);
    drivetrainCoolFalconsButton.whenPressed(new DrivetrainCoolFalcons());

    // // FUNNEL
    // Button funnelMoveBallsForwardButton = new JoystickButton(this.operatorGamepad,
    //     Robot.ROBOT_MAP.funnelMoveBallsForwardButton);
    // funnelMoveBallsForwardButton.whenPressed(new FunnelMoveBalls());

    // INTAKE
    Button intakeSpinRollerForwardButton = new JoystickButton(this.operatorGamepad,
        Robot.ROBOT_MAP.intakeSpinRollerForwardButton);
    intakeSpinRollerForwardButton.whenPressed(new IntakeSpinRoller());
    intakeSpinRollerForwardButton.whenReleased(new IntakeStop());

    Button intakeToggleSolenoidButton = new JoystickButton(this.operatorGamepad,
        Robot.ROBOT_MAP.intakeToggleSolenoidButton);
    intakeToggleSolenoidButton.whenPressed(new IntakeToggleSolenoid());

    // SHOOTER
    Button shooterSpinForwardButton = new JoystickButton(this.operatorGamepad,
        Robot.ROBOT_MAP.shooterSpinForwardButton);
    shooterSpinForwardButton.whenPressed(new SequentialCommandGroup(/* request hex detection, */ new ShooterSpool()
    ,new ShooterSpin()));
    shooterSpinForwardButton.whenReleased(new ShooterStop());

    // STORAGE
    Button storageMoveBallsForwardButton = new JoystickButton(this.operatorGamepad,
        Robot.ROBOT_MAP.storageMoveBallsForwardButton);
    storageMoveBallsForwardButton.whenPressed(new StorageMoveBalls());
    storageMoveBallsForwardButton.whenReleased(new StorageStop());

    // VISION
    Button alignButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.visionAlignButton);
    alignButton.whenPressed(new Align());

    Button bayButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.bayButton);
    bayButton.whenPressed(new RequestBay());

    Button ballButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.ballButton);
    ballButton.whenPressed(new RequestBall());

    Button portButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.portButton);
    portButton.whenPressed(new RequestPort());

    Button connectButton = new JoystickButton(this.driveJoystick, Robot.ROBOT_MAP.connectButton);
    connectButton.whenPressed(new Connect());

    // VOMIT
    Button vomitButton = new JoystickButton(this.operatorGamepad, Robot.ROBOT_MAP.vomitButton);
    vomitButton.whenPressed(
        new ParallelCommandGroup(new FunnelVomit(),new IntakeVomit(), new ShooterVomit(), new StorageVomit()));

    // PATHCORDER
    Button startRecordingButton = new JoystickButton(Robot.COMMAND_LINKER.driveJoystick,
        Robot.ROBOT_MAP.startRecordingButton);
    startRecordingButton.whenPressed(new StartRecording());

    Button endRecordingButton = new JoystickButton(Robot.COMMAND_LINKER.driveJoystick,
        Robot.ROBOT_MAP.endRecordingButton);
    endRecordingButton.whenPressed(new EndRecording());
  }
}
