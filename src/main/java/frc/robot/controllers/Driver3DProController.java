/*----------------------------------------------------------------------------*/
/*                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import frc.robot.Robot;

import frc.robot.commands.drivetrain.DrivetrainCoolFalcons;
import frc.robot.commands.drivetrain.DrivetrainReverseDirection;
import frc.robot.commands.drivetrain.DrivetrainShiftGear;

import frc.robot.commands.vision.Align;
import frc.robot.commands.vision.Connect;
import frc.robot.commands.vision.RequestBall;
import frc.robot.commands.vision.RequestBay;
import frc.robot.commands.vision.RequestPort;

public class Driver3DProController {

  private final Controller controller

  public Driver3DProController(joystickPort){
    this.controller = new Controller(joystickPort)
  }

  // replace Robot.ROBOT_Map.button_name with corresponding buttons on joystick

  public mapButtons() {

    // DRIVETRAIN
    this.controller.mapButton(1)
      .whenPressed(new DrivetrainShiftGear());

    this.controller.mapButton(2)
        .whenPressed(new DrivetrainReverseDirection());

    this.controller.mapButton(3)
      .whenPressed(new DrivetrainCoolFalcons());

    // VISION
    this.controller.mapButton(Robot.ROBOT_MAP.visionAlignButton)
      .whenPressed(new Align());

    this.controller.mapButton(Robot.ROBOT_MAP.bayButton)
      .whenPressed(new RequestBay());

    this.controller.mapButton(Robot.ROBOT_MAP.ballButton)
      .whenPressed(new RequestBall());

    this.controller.mapButton.(Robot.ROBOT_MAP.portButton)
      .whenPressed(new RequestPort());

    this.controller.mapButton(Robot.ROBOT_MAP.connectButton)
      .whenPressed(new Connect());

  }
}
