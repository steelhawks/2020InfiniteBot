/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.shooter.ShooterSpoolVision;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter.ShooterSpinVision;
import frc.robot.commands.storage.StorageMoveBalls;
import frc.robot.commands.storage.StorageStop;
import frc.robot.commands.shooter.ShooterSpin;
import frc.robot.commands.intake.IntakeSpinRoller;
import frc.robot.commands.intake.IntakeStop;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import java.util.Set;
import java.util.HashSet;

public class MiracleAlign implements Command
{
    public MiracleAlign() {}

    @Override
    public Set<Subsystem> getRequirements() 
    {
        Set<Subsystem> list = new HashSet<Subsystem>();
        list.add(Robot.DRIVETRAIN);
        list.add(Robot.VISION);
        return list;
    }

    @Override
    public void initialize()
    {
      // if camera mode is not hex, only one press to start/stop alignment
      if(!(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")))
      {
        Robot.VISION.isPressed = false;
      }

      // if camera mode is hex and button wasn't pressed yet, start alignment
      if(!(Robot.VISION.isPressed))
      {
        if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData()))
        {
            Robot.DRIVETRAIN.gyro.reset();
            Robot.DRIVETRAIN.stop();
            Robot.DRIVETRAIN.isForward = true;
            Robot.VISION.reset();
            Robot.VISION.setAngle(Robot.VISION.getNTAngle());
            Robot.VISION.setXPosLeftLimit(315.0);
            Robot.VISION.setXPosRightLimit(325.0);
            Robot.VISION.setXPosOffset(-4.5);
            if(Robot.DASHBOARDWS.cameraMode.equals("BALL")){
              Robot.INTAKE.down();
            }
        }
        else
        {
            if(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")){
              System.out.println("Already pressed, STOPPING SUBSYSTEMS");
            }
            System.out.println("No object to align to...");
        }
      }
    }

    @Override
    public void execute()
    {
        // if object is present then start alignment for objects
        if (!(Robot.VISION.isPressed) && Robot.VISION.objectIsPresent)
        {

            if(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON"))
            {
                Robot.DRIVETRAIN.lowGear();
                Robot.VISION.align();
            }
            else if(Robot.DASHBOARDWS.cameraMode.equals("BALL")){
                Robot.DRIVETRAIN.highGear();
                Robot.INTAKE.spinRoller(true);
                Robot.VISION.alignCurve();
            }
            else if(Robot.DASHBOARDWS.cameraMode.equals("BAY"))
            {
                Robot.DRIVETRAIN.highGear();
                Robot.VISION.alignCurve();
            }
        }
        else
        {
            System.out.println("No object to align to...");
        }
    }

    @Override
    public boolean isFinished()
    {
      boolean finished;
      if(!(Robot.VISION.isPressed)){
        finished = Robot.VISION.isAligned();
      }
      else 
      {
        finished = true; 
      }
      return finished;
     
    }

    @Override
    public void end(boolean interrupted)
    {         
        Robot.DRIVETRAIN.drivetrainLeftMotorGroup.set(0);
        Robot.DRIVETRAIN.drivetrainRightMotorGroup.set(0);
        Robot.DRIVETRAIN.isForward = true;

        if(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")){
          // spool shootor, adjust velocity based on distance and start moving the balls
          if(!(Robot.VISION.isPressed)){
            CommandScheduler.getInstance().schedule(
            new SequentialCommandGroup(
              new ShooterSpoolVision(), 
              new ParallelCommandGroup(
                new ShooterSpinVision(), new StorageMoveBalls())));
          }
          else{
            // if button was pressed, stop shooter and storage
            CommandScheduler.getInstance().schedule(new ParallelCommandGroup(
              new ShooterStop(), new StorageStop()));
          }
          
        }
        else if(Robot.DASHBOARDWS.cameraMode.equals("BAY")){
          Robot.INTAKE.stop();
        }
    
        Robot.VISION.press();
    }
}