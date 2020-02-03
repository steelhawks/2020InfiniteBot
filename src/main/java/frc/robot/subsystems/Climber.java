// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import frc.robot.Robot;

// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// public class Climber extends SubsystemBase
// {
//     // motor for winch
//     public WPI_TalonSRX climberMotorOne;

//     // speed controller group
//     public SpeedControllerGroup climberMotorGroup;

//     // piston
//     public DoubleSolenoid climberSol;

//     // climber constructor
//     public Climber() 
//     {
//         //Initializing
//         this.climberMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.climbMotorPort);
//         this.climberMotorGroup = new SpeedControllerGroup(this.climberMotorOne);
//         this.climberSol = new DoubleSolenoid(Robot.ROBOTMAP.climberSolOnPort, Robot.ROBOTMAP.climberSolOffPort);
//     }
    
//     // toggle
//     public void toggleSolenoid()
//     {
//         if (this.climberSol.get().equals(DoubleSolenoid.Value.kForward))
//         {
//             retractSolenoid();
//         }
//         else
//         {
//             extendSolenoid();
//         }
//     }

//     // up button
//     public void retractSolenoid()
//     {
//         this.climberSol.set(DoubleSolenoid.Value.kReverse);
//     }

//     // down button
//     public void extendSolenoid()
//     {
//         this.climberSol.set(DoubleSolenoid.Value.kForward);
//     }

//     // winch
//     public void rollWinch(boolean isForward)
//     {
//         if(isForward)
//         {
//             this.climberMotorGroup.set(Robot.ROBOTMAP.climberSpeed);
//         }
//         else
//         {
//             this.climberMotorGroup.set(-Robot.ROBOTMAP.climberSpeed);
//         }
//     }

//     // smart dashboard
//     public void smartDashboard()
//     {
//     //     SmartDashboard.putBoolean("Climber Solenoid State", (this.climberSol.get() == DoubleSolenoid.Value.kForward) ? true : false);
//     //     SmartDashboard.putNumber("Climber Motor One Speed", this.climberMotorOne.get());
//     // }

//     // stop
//     public boolean stopClimber()
//     {
//         climberMotorGroup.stopMotor();
//         return true;
//     }
// }