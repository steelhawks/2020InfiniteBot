
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class IntakeClimb implements Command {
  public IntakeClimb() {
  }

  @Override
  public Set<Subsystem> getRequirements() {
    Set<Subsystem> list = new HashSet<Subsystem>();
    list.add(Robot.INTAKE);
    return list;
  }
  
  @Override
  public void initialize() {
  }
  
  @Override
  public void execute() {
      Robot.INTAKE.climbInitate();
  }

  @Override
  public boolean isFinished() {
    return true;
  }

  @Override
  public void end(boolean interrupted) {
    Robot.INTAKE.stop();
  }
}


/*is coding is making mistakes
is doing werk
but not rlly
coding is fun but not rllycoding coding codingthis is what i've contributed to the code
guys - code code code code code code code code code code code cod e code code code code
code code code 
code code code 
code code code code code code code code code code code code code code code cod eocde code code
code code code
code code
code
code
codecodoecode code code code code code code code code code code code code
code code code code code code code code code code code code code code code code code code
code code code code code code cod e
*/
