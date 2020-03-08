/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.Robot;
import frc.robot.commands.climber.ClimberToggleSafetySolenoid;
import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
import frc.robot.commands.drivetrain.DrivetrainShiftGear;
import frc.robot.commands.pathcorder.EndRecording;
import frc.robot.commands.pathcorder.StartRecording;
import frc.robot.commands.turret.TurretTest;
import frc.robot.commands.vision.VisionMiracleAlign;
import frc.robot.commands.vision.VisionConnect;
import frc.robot.commands.vision.VisionRequestBall;
import frc.robot.commands.vision.VisionRequestBay;
import frc.robot.commands.vision.VisionRequestPort;


public class Driver3DProController {

  private final Controller controller;

  public Driver3DProController(Joystick joystickPort) {
    this.controller = new Controller(joystickPort);
  }

  public void mapButtons() {

    // DRIVETRAIN
    this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton)
      .whenPressed(new DrivetrainShiftGear());

    this.controller.mapButton(Robot.BUTTON_MAP.drivetrainReverseDirectionButton)
        .whenPressed(new DrivetrainReverseDirection());

    // VISION
    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignButton)
      .whenPressed(new VisionMiracleAlign());

    this.controller.mapButton(Robot.BUTTON_MAP.visionRequestBayButton)
      .whenPressed(new VisionRequestBay());

    this.controller.mapButton(Robot.BUTTON_MAP.visionRequestBallButton)
      .whenPressed(new VisionRequestBall());

    this.controller.mapButton(Robot.BUTTON_MAP.visionRequestPortButton)
      .whenPressed(new VisionRequestPort());

    this.controller.mapButton(Robot.BUTTON_MAP.visionRequestConnectButton)
      .whenPressed(new VisionConnect());

    this.controller.mapButton(Robot.BUTTON_MAP.startRecordingButton)
      .whenPressed(new StartRecording());

      this.controller.mapButton(Robot.BUTTON_MAP.endRecordingButton)
      .whenPressed(new EndRecording());

    this.controller.mapButton(Robot.BUTTON_MAP.climberToggleSafetySolenoidButton)
      .whenPressed(new ClimberToggleSafetySolenoid());

    this.controller.mapButton(Robot.BUTTON_MAP.turretToggleTestingButton)
      .whenPressed(new TurretTest());

  }
}
