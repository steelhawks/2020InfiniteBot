/*----------------------------------------------------------------------------*/
/*                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Robot;

import frc.robot.commands.drivetrain.DrivetrainCoolFalcons;
import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
import frc.robot.commands.drivetrain.DrivetrainShiftGear;

import frc.robot.commands.vision.MiracleAlign;
import frc.robot.commands.vision.Connect;
import frc.robot.commands.vision.RequestBall;
import frc.robot.commands.vision.RequestBay;
import frc.robot.commands.vision.RequestPort;
import frc.robot.commands.pathcorder.EndRecording;
import frc.robot.commands.pathcorder.StartRecording;
import frc.robot.commands.vision.LiftCamera;


public class Driver3DProController {

  private final Controller controller;

  public Driver3DProController(Joystick joystickPort){
    this.controller = new Controller(joystickPort);
  }

  // replace Robot.ROBOT_Map.button_name with corresponding buttons on joystick

  public void mapButtons() {

    // DRIVETRAIN
    this.controller.mapButton(Robot.BUTTON_MAP.drivetrainShiftButton)
      .whenPressed(new DrivetrainShiftGear());

    this.controller.mapButton(Robot.BUTTON_MAP.drivetrainReverseDirectionButton)
        .whenPressed(new DrivetrainReverseDirection());

    // this.controller.mapButton(3)
    //   .whenPressed(new DrivetrainCoolFalcons());

    // VISION
    this.controller.mapButton(Robot.BUTTON_MAP.visionAlignButton)
      .whenPressed(new MiracleAlign());

    this.controller.mapButton(Robot.BUTTON_MAP.bayButton)
      .whenPressed(new RequestBay());

    this.controller.mapButton(Robot.BUTTON_MAP.ballButton)
      .whenPressed(new RequestBall());

    this.controller.mapButton(Robot.BUTTON_MAP.portButton)
      .whenPressed(new RequestPort());

    this.controller.mapButton(Robot.BUTTON_MAP.connectButton)
      .whenPressed(new Connect());

    this.controller.mapButton(Robot.BUTTON_MAP.startRecordingButton)
      .whenPressed(new StartRecording());

      this.controller.mapButton(Robot.BUTTON_MAP.endRecordingButton)
      .whenPressed(new EndRecording());

    this.controller.mapButton(Robot.BUTTON_MAP.cameraButton)
      .whenPressed(new LiftCamera());

  }
}
