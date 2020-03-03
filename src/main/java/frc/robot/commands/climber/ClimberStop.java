package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

public class ClimberStop extends CommandBase {

  public ClimberStop() {
    addRequirements(Robot.CLIMBER);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    Robot.CLIMBER.stopClimb();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    if (interrupted) {
      Robot.CLIMBER.stopClimb();
    }
  }
}