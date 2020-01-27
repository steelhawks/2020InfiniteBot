package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

public class Climber extends SubsystemBase
{
    // motor for winch
    public WPI_TalonSRX climberMotorOne;

    // speed controller group
    public SpeedControllerGroup climberMotorGroup;

    // piston
    public Solenoid climberPiston;

    // public int target;


    // climber constructor
    public Climber() 
    {
        //Initializing
        this.climberMotorOne = new WPI_TalonSRX(Robot.ROBOTMAP.climbMotorPort);
        this.climberMotorGroup = new SpeedControllerGroup(this.climberMotorOne);
        // this.target = 0;
        
    }

    //speed controller
    public void controlSpeed()
    {
        climberMotorGroup.set(0.5);
    }

    // up button
    public void climberUpButton()
    {
        climberPiston.set(false);
    }

    // down button
    public void climberDownButton()
    {
        climberMotorGroup.set(0.5);
        climberPiston.set(true);
    }

    // stop
    public void stopClimber()
    {
        climberMotorGroup.set(0);
    }
}