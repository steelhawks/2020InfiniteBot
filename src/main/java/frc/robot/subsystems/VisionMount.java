package frc.robot.subsystems;
 import frc.robot.Robot;
 import edu.wpi.first.wpilibj.DoubleSolenoid;
 import frc.util.subsystems.MechanicalSubsystem;

 public class VisionMount extends MechanicalSubsystem
 {
    
   public DoubleSolenoid visionPiston = new DoubleSolenoid(Robot.ROBOT_MAP.visionSolOnPort,Robot.ROBOT_MAP.visionSolOffPort);

   public VisionMount()
   {
   }
   public void extendSolenoid() {
    this.visionPiston.set(DoubleSolenoid.Value.kForward);
  }

  public void retractSolenoid() {
    this.visionPiston.set(DoubleSolenoid.Value.kReverse);
  }

  public void pistonPosition() {
    if (this.visionPiston.get().equals(DoubleSolenoid.Value.kReverse)) {
      System.out.println("extending");
      extendSolenoid();
    } else {
      System.out.println("retracting");
      retractSolenoid();
    }
  }

  public boolean isAlive() {
    return true;
  }

  public void ping() {
  }

  public boolean stop() {
    return true;
  }

  public void smartDashboard() {
    // SmartDashboard.putBoolean("Vision Mount Solenoid State",  
    //    (this.visionPiston.get() == DoubleSolenoid.Value.kForward) ? true : false);
  }

 }
