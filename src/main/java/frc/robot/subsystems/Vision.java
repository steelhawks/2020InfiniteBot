/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;

import frc.util.subsystems.VisionSubsystem;

import org.json.*;

public class Vision extends VisionSubsystem {

  private boolean isAligned;
  private double angle;
  private double xPosLeftLimit;
  private double xPosRightLimit;
  private double xPosOffset;
  private JSONObject closestTarget;
  public boolean objectIsPresent;
  public boolean isPressed = false;
  public int alignCount = 0;

  public void alignCurve() {
    double tuneValue = 300;

    if (Robot.COMMAND_LINKER.driveJoystick.getRawButtonPressed(2)) {
      end();
    } else if (getDistance() > 35) {
      if (Math.abs(Robot.DRIVETRAIN.gyro.getAngle()) < getNTAngle()
          && getXPos() - getXPosOffset() < getXPosLeftLimit()) {
        Robot.DRIVETRAIN.drivetrainLeftMotorGroup.set(-(getDistance()) / tuneValue);
        Robot.DRIVETRAIN.drivetrainRightMotorGroup
            .set((getDistance() * (getXPosDiff(getXPosLeftLimit()) * 0.3)) / tuneValue);
      } else if (Math.abs(Robot.DRIVETRAIN.gyro.getAngle()) < getNTAngle()
          && getXPos() - getXPosOffset() > getXPosRightLimit()) {
        Robot.DRIVETRAIN.drivetrainLeftMotorGroup
            .set(-(getDistance() * (getXPosDiff(getXPosRightLimit()) * 0.3)) / tuneValue);
        Robot.DRIVETRAIN.drivetrainRightMotorGroup.set((getDistance()) / tuneValue);
      } else {
        Robot.DRIVETRAIN.drivetrainLeftMotorGroup.set(-(getDistance()) / tuneValue);
        Robot.DRIVETRAIN.drivetrainRightMotorGroup.set((getDistance()) / tuneValue);
      }
    } else {
      Robot.DRIVETRAIN.drivetrainLeftMotorGroup.set(0);
      Robot.DRIVETRAIN.drivetrainRightMotorGroup.set(0);
      
      Robot.VISION_LIGHT.disable();
      Robot.STRIP_LIGHT.aligned();
      this.isAligned = true;
    }
  }

  // public void align() {
  //   if (Robot.COMMAND_LINKER.driveJoystick.getRawButtonPressed(2)) {
  //     end();
  //   } else if (!this.isAligned) {
  //     System.out.println("Aligning " + getXPosDiff(getXPos() - getXPosOffset()) + " xposoffset " + getXPosOffset());
  //     if (getXPos() - getXPosOffset() < getXPosLeftLimit()) {
  //       System.out.println("right");
  //       Robot.DRIVETRAIN.rotate(0.3 * (getXPosDiff(getXPos() - getXPosOffset()) / 320) - (-0.31));
  //       System.out.println(0.3 * (getXPosDiff(getXPos() - getXPosOffset()) / 320) - (-0.31));
  //     } else if (getXPos() - getXPosOffset() > getXPosRightLimit()) {
  //       System.out.println("left");
  //       Robot.DRIVETRAIN.rotate(-0.5 * (getXPosDiff(getXPos() - getXPosOffset()) / 320) + (-0.31));
  //       System.out.println(-0.5 * (getXPosDiff(getXPos() - getXPosOffset()) / 320) + (-0.31));
  //     } else {
  //       Robot.DRIVETRAIN.gyro.reset();
  //       Robot.DRIVETRAIN.stop();
  //       System.out.println("Aligned!");
        
  //       Robot.VISION_LIGHT.disable();
  //       Robot.STRIP_LIGHT.aligned();
  //       this.isAligned = true;
  //     }
  //   }
  // }

  // FOR TURRET, if looking for drivetrain it is the method above *********
  
  public void align() {
    if (Robot.COMMAND_LINKER.driveJoystick.getRawButtonPressed(2)) {
      end();
    } else if (!this.isAligned) {
      System.out.println("Aligning");
      if (getXPos()< getXPosLeftLimit() && Robot.TURRET.rightLimitSwitch.get()) {
        System.out.println("right");
        Robot.TURRET.turretMotorGroup.set(0.025 * (getXPosDiff(getXPos()) / 320) - (-0.1));
      } else if (getXPos()> getXPosRightLimit() && Robot.TURRET.leftLimitSwitch.get()) {
        System.out.println("left");
        Robot.TURRET.turretMotorGroup.set(-0.025 * (getXPosDiff(getXPos()) / 320) + (-0.1));
      } else {
        Robot.TURRET.stop();
        System.out.println("Aligned!");
        
        Robot.VISION_LIGHT.disable();
        Robot.STRIP_LIGHT.aligned();
        this.isAligned = true;
      }
    }
  }

  public boolean containsData(String data) {
    if (data.equals("{\"target\":[]}")) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public boolean objectPresent(String trackingData) {
    try {
      if (containsData(trackingData)) {
        JSONObject targetData = new JSONObject(trackingData);
        String targetDataValues = (targetData.getString("targets"));
        JSONArray targetArray = new JSONArray(targetDataValues);
        closestTarget = (JSONObject) targetArray.get(0);
        this.objectIsPresent = true;
        return true;
      } else {
        this.objectIsPresent = false;
        return false;
      }
    } catch (Exception e) {
      return false;
    }
  }

  public void press() {
    if (this.isPressed == true) {
      this.isPressed = false;
    } else {
      this.isPressed = true;
    }
  }

  public String getShape() {
    try {
      return closestTarget.getString("shape");
    } catch (Exception e) {
      return "object not found";
    }
  }

  public double getXPos() {
    try {
      System.out.println(getShape() + " Center X: " + closestTarget.getDouble("xpos"));
      return closestTarget.getInt("xpos");
    } catch (Exception e) {
      return 0;
    }
  }

  public double getYPos() {
    try {
      System.out.println(getShape() + " Center Y: " + closestTarget.getDouble("ypos"));
      return closestTarget.getInt("ypos");
    } catch (Exception e) {
      return 0;
    }
  }

  public double getDistance() {
    try {
      System.out.println(getShape() + " Distance: " + closestTarget.getDouble("dist"));
      return closestTarget.getInt("dist");
    } catch (Exception e) {
      return 0;
    }
  }

  public double getNTAngle() {
    try {
      System.out.println(getShape() + " Angle to Line of Vision: " + closestTarget.getDouble("angle"));
      return closestTarget.getDouble("angle");
    } catch (Exception e) {
      return 0;
    }
  }

  public void setAngle(double angle) {
    this.angle = angle;
  }

  public double getAngle() {
    return this.angle;
  }

  public void setXPosLeftLimit(double xPosLeftLimit) {
    this.xPosLeftLimit = xPosLeftLimit;
  }

  public void setXPosRightLimit(double xPosRightLimit) {
    this.xPosRightLimit = xPosRightLimit;
  }

  public void setXPosOffset(double xPosOffset) {
    this.xPosOffset = xPosOffset;
  }

  public double getXPosLeftLimit() {
    return this.xPosLeftLimit;
  }

  public double getXPosRightLimit() {
    return this.xPosRightLimit;
  }

  public double getXPosOffset() {
    return this.xPosOffset;
  }

  public double getXPosDiff(double xPos) {
    if (xPos > 320) {
      return xPos - 320;
    }
    return Math.abs(320 - xPos);
  }

  public boolean isAligned() {
    return this.isAligned;
  }

  @Override
  public void enable() {
  }

  @Override
  public void disable() {
  }

  @Override
  public void ping() {
  }

  @Override
  public boolean isAlive() {
    return SmartDashboard.getBoolean("Tracking WebSocket", false);
  }

  public void end() {
    Robot.DRIVETRAIN.stop();
    Robot.VISION_LIGHT.disable();
    System.out.println("Quit...");
    this.isAligned = true;
  }

  public void reset() {
    this.isAligned = false;
    this.alignCount = 0;
  }
}