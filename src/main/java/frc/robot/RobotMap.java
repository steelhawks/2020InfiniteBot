/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.turret.TurretReturnToZero;

public final class RobotMap {
  /*****
   * Quirks
   */

  public final String driver = "{[INSERT_DRIVER]}";
  public final String operator = "{[INSERT_OPERATOR]}";

  /*****
   * Networking & Auton
   */

  public final String jetsonNanoIP = "10.26.1.74";
  public final String driveStationIP = "10.26.1.5";
  public final String selfIP = "10.26.1.2";

  public final String jetsonNanoPort = "5805";

  public final String pathName = "/home/lvuser/deploy/log_2020-02-14_212213.csv";
  public String[] paths = {"straight.csv", "back.csv"};
  public final String deployDirectory = "C:/Code/2020AutonBot/deploy";
  public SequentialCommandGroup autonCommands = new SequentialCommandGroup(new TurretReturnToZero());

  /*****
   * Drivetrain
   */

  public final int drivetrainLeftMotorOnePort = 1;
  public final int drivetrainLeftMotorTwoPort = 2;
  public final int drivetrainLeftMotorThreePort = 3;
  
  public final int drivetrainRightMotorOnePort = 4;
  public final int drivetrainRightMotorTwoPort = 5;
  public final int drivetrainRightMotorThreePort = 6;

  public final int drivetrainSolOnPort = 0;
  public final int drivetrainSolOffPort = 1;

  /*****
   * Climber
   */

  public final int climbMotorOnePort = 7;

  public final int safetySolOnPort = 2;
  public final int safetySolOffPort = 3;

  public final int climberSolOnPort = 4;
  public final int climberSolOffPort = 5;

  public final double climberSpeed = 1.0;

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
  public final int storageMotorThreePort = 15;
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

  public final int turretMotorOnePort = 14;

  public final int turretZeroPos = -10500;
  public final int turretQuarterPos = 0;

  public final int turretLeftLimitSwitchPort = 0;
  public final int turretRightLimitSwitchPort = 1;

  public final int turretLeftEncoderLimit = 0;
  public final int turretRightEncoderLimit = turretZeroPos * 2;

  /*****
   * Vision
   */

  public final int visionLightPortOne = 12;

  public final int stripLightPortOne = 0;

  public final double visionLightIntensity = 1.0;
}
