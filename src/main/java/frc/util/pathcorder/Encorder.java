/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.pathcorder;

import com.revrobotics.CANEncoder;

public class Encorder {

  public double rightEncoder;
  public double leftEncoder;

  public Encorder(CANEncoder right, CANEncoder left) {

    rightEncoder = right.getPosition();
    leftEncoder = left.getPosition();
  }


  public String toString() {
    return ("Right Encoder: " + this.rightEncoder + "\nLeft Encoder: " + this.leftEncoder);

  }

}
