/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controllers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {

  private final Joystick joystick;

  public Controller(Joystick joystick) {
    this.joystick = joystick;
  }

  public JoystickButton mapButton(int buttonNumber){
    return new JoystickButton(this.joystick, buttonNumber);
  }

}
