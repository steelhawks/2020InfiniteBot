/*----------------------------------------------------------------------------*/
/*                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import frc.robot.Robot;
import frc.robot.commands.climber.ClimberRollWinch;
import frc.robot.commands.climber.ClimberToggleSolenoid;
import frc.robot.commands.climber.ClimberStop;
import frc.robot.commands.climber.ClimberUnrollWinch;

public class OperatorXBoxController {

  private final Controller controller

  public OperatorXBoxController(joystickPort){
    this.controller = new Controller(joystickPort)
  }

  // replace Robot.ROBOT_Map.button_name with corresponding buttons on joystick

  public mapButtons() {

    // CLIMBER
    this.controller.mapButton(Robot.ROBOT_MAP.climberToggleSolenoidButton)
      .whenPressed(new ClimberToggleSolenoid());

    this.controller.mapButton(Robot.ROBOT_MAP.climberUnrollButton)
      .whenPressed(new ClimberUnrollWinch());
      .whenReleased(new ClimberStop());

    this.controller.mapButton(Robot.ROBOT_MAP.climberRollWinchButton)
      .whenPressed(new ClimberRollWinch());
      .whenReleased(new ClimberStop());
  }
}
