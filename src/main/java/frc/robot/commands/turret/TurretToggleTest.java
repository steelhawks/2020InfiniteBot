/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class TurretToggleTest extends CommandBase {
  public TurretToggleTest() {
    addRequirements(Robot.TURRET);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(Robot.TURRET.testing == true){
      Robot.TURRET.testing = false;
    }
    else{
      Robot.TURRET.testing = true;
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
  }

}