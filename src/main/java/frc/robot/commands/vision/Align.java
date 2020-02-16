/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;
import java.util.Set;
import java.util.HashSet;

public class Align implements Command
{
    public Align() {}

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
        if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData()))
        {
            Robot.DRIVETRAIN.gyro.reset();
            Robot.DRIVETRAIN.stop();
            Robot.DRIVETRAIN.isForward = true;
            Robot.VISION.reset();
            Robot.VISION.setAngle(Robot.VISION.getNTAngle());
            Robot.VISION.setXPosLeftLimit(315.0);
            Robot.VISION.setXPosRightLimit(325.0);
            Robot.VISION.setXPosOffset(4.5);
        }
        else
        {
            System.out.println("No object to align to...");
        }
    }

    @Override
    public void execute()
    {
        
        if (Robot.VISION.objectPresent(Robot.TRACKINGWS.getTargetData()))
        {
            String cameraMode = Robot.DASHBOARDWS.getCameraMode();
            if(cameraMode.equals("HEXAGON"))
            {
                Robot.DRIVETRAIN.lowGear();
                Robot.VISION.align();
            }
            else if(cameraMode.equals("BAY") || cameraMode.equals("BALL"))
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
        return Robot.VISION.isAligned();
    }

    @Override
    public void end(boolean interrupted)
    {
        Robot.DRIVETRAIN.drivetrainLeftMotorGroup.set(0);
        Robot.DRIVETRAIN.drivetrainRightMotorGroup.set(0);
        Robot.DRIVETRAIN.isForward = true;
    }
}