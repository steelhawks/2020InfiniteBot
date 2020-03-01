/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.commands.vision.*;
import frc.robot.commands.pathcorder.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public final class RobotMap {
  /*****
   * Quirks
   */

  public final String driver = "{[INSERT_DRIVER]}";
  public final String operator = "{[INSERT_OPERATOR]}";

  /*****
   * Networking & Auton
   */

  public final String jetsonNanoIP = "10.26.1.102";
  public final String driveStationIP = "10.26.1.5";
  public final String selfIP = "10.26.1.2";

  public final String jetsonNanoPort = "5805";

  public final String pathName = "/home/lvuser/deploy/log_2020-02-14_212213.csv";
  public final String[] paths = {"straight.csv", "back.csv"};
  public final String deployDirectory = "C:/Code/2020AutonBot/deploy";
  public SequentialCommandGroup autonCommands;

  /*****
   * Drivetrain
   */

  public final int drivetrainLeftMotorOnePort = 1;
  public final int drivetrainLeftMotorTwoPort = 2;
  public final int drivetrainLeftMotorThreePort = 3;
  
  public final int drivetrainRightMotorOnePort = 6;
  public final int drivetrainRightMotorTwoPort = 5;
  public final int drivetrainRightMotorThreePort = 4;

  public final int drivetrainSolOnPort = 0;
  public final int drivetrainSolOffPort = 1;

  public final int visionSolOnPort = 2;
  public final int visionSolOffPort = 3;

  /*****
   * Climber
   */

  public final int climbMotorOnePort = 7;

  public final int climberSolOnPort = 4;
  public final int climberSolOffPort = 5;

  public final double climberSpeed = 1.0;

  /*****
   * Funnel
   */

  public final int funnelMotorOnePort= 9;

  public final double funnelSpeed = 0.5;

  /*****
   * Intake
   */

  public final int intakeMotorOnePort = 8;

  public final int intakeSolOnPort = 6;
  public final int intakeSolOffPort = 7;

  public final double intakeSpeed = 1;

  /*****
   * Storage
   */

  public final int storageMotorOnePort = 13;
  public final int storageMotorTwoPort = 14;
  public final double storageSpeedOne = 1.0;
  public final double storageSpeedTwo = 1.0;

  /*****
   * Shooter
   */

  public final int shooterMotorOnePort = 10;
  public final int shooterMotorTwoPort = 11;

  public final int shooterMaxRPM = 3000;
  
  public final double distanceToShooterVelocity = 0.21;

  /*****
   * Turret
   */

  public final int turretMotorOnePort = 16;

  /*****
   * Vision
   */

  public final int visionLightPortOne = 12;

  public final double visionLightIntensity = 1.0;
}
