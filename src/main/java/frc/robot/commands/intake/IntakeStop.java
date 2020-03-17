/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class IntakeStop extends CommandBase {
  public IntakeStop() {
    addRequirements(Robot.INTAKE);
  }

  @Override
  public void initialize() {
    if(Robot.RECORDER.isRecording){
      Robot.RECORDER.buttonPressed = -Robot.BUTTON_MAP.intakeSpinRollerForwardButton;
    }
  }

  @Override
  public void execute() {
    Robot.INTAKE.spinRoller(0.0);
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted)
    {
      Robot.INTAKE.stop();
    }
  }
}