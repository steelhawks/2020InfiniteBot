/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.shooter.ShooterSpool;
import frc.robot.commands.shooter.ShooterStop;
import frc.robot.commands.shooter.ShooterSpin;
import frc.robot.commands.storage.StorageMoveBalls;
import frc.robot.commands.storage.StorageReverseBalls;
import frc.robot.commands.storage.StorageStop;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import java.util.Set;
import java.util.HashSet;

public class VisionMiracleAlign implements Command
{
    public VisionMiracleAlign() {}

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
      Robot.DASHBOARDWS.getCameraMode();
      // if camera mode is not hex, only one press to start/stop alignment
      if(!(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON")))
      {
        Robot.VISION.isPressed = false;
      }
      // if camera mode is hex and button wasn't pressed yet, start alignment
      if(!(Robot.VISION.isPressed))
      {
        System.out.println("passed");
        if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData()))
        {
            System.out.println("initialized");
            Robot.DRIVETRAIN.gyro.reset();
            Robot.DRIVETRAIN.stop();
            Robot.DRIVETRAIN.isForward = true;
            Robot.VISION.reset();
            Robot.VISION.setAngle(Robot.VISION.getNTAngle());
            Robot.VISION.setXPosLeftLimit(315.0);
            Robot.VISION.setXPosRightLimit(325.0);
            Robot.VISION.setXPosOffset(3.11 * Math.pow(10, -3) * Robot.VISION.getDistance() - 5.28);
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
        if (!(Robot.VISION.isPressed) && Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData()))
        {
            System.out.println("1");
            if(Robot.DASHBOARDWS.cameraMode.equals("HEXAGON"))
            {
                System.out.println("2");
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
            System.out.println("No object to align to...1123");
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
          //spools shooter first, then reverses the storage 
          //and feeds the balls in as the shooter spins
          if(!(Robot.VISION.isPressed)){
            CommandScheduler.getInstance().schedule(
            new SequentialCommandGroup(
              new ShooterSpool(), 
              new ParallelCommandGroup(
                new SequentialCommandGroup(
                  new StorageReverseBalls(), new StorageMoveBalls(),
                new ShooterSpin() 
                ))));
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