/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.RobotMap;;



public class LimitSwitch {
//   static RobotMap constants = new RobotMap();
//   public DigitalInput topLimit = new DigitalInput(0);
//   public DigitalInput bottomLimit = new DigitalInput(1);


//     DigitalInput forwardLimitSwitch, reverseLimitSwitch;
//     WPI_TalonSRX motor;
//     Joystick joystick1;
//     public LimitSwitch(){   
//         forwardLimitSwitch = new DigitalInput(1);
//         reverseLimitSwitch = new DigitalInput(2);
//         motor = new WPI_TalonSRX(1);
//         joystick1 = new Joystick(1);
//     }

//     public void teleopPeriodic()
//     {
//         double output = joystick1.getY(); //Moves the joystick based on Y value
//         if (forwardLimitSwitch.get()) // If the forward limit switch is pressed, we want to keep the values between -1 and 0
//             output = Math.min(output, 0);
//         else if(reverseLimitSwitch.get()) // If the reversed limit switch is pressed, we want to keep the values between 0 and 1
//             output = Math.max(output, 0);
//         motor.set(output);
//     }
//     public void stop() {
//     // set to 0 
//       }


}