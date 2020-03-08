/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class TurretDefault extends CommandBase {
  public TurretDefault() {
    addRequirements(Robot.TURRET);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(Robot.TURRET.testing && Robot.TURRET.leftLimitSwitch.get() && Robot.TURRET.rightLimitSwitch.get()){
      System.out.println(Robot.TURRET.turretMotorOne.getSelectedSensorPosition());
      Robot.TURRET.turretMotorOne.set(Robot.COMMAND_LINKER.driveJoystick.getRawAxis(3) / 5);
    }
  }

  @Override
  public boolean isFinished() {
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    if(interrupted)
    {
      Robot.TURRET.stop();
    }
  }

}