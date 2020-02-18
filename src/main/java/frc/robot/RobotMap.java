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

  public final String driver = "{[INSERT_OPERATOR]}";
  public final String operator = "{[INSERT_OPERATOR]}";

  /*****
   * Networking & Auton
   */

  public final String jetsonNanoIP = "10.26.1.7";
  public final String driveStationIP = "10.26.1.5";
  public final String selfIP = "10.26.1.2";

  public final String jetsonNanoPort = "5805";

  public final String pathName = "/home/lvuser/deploy/log_2020-02-14_212213.csv";
  public final String[] paths = {"straight.csv", "back.csv" };
  public final String deployDirectory = "C:/Code/2020AutonBot/deploy";
  public final SequentialCommandGroup autonCommands = 
    new SequentialCommandGroup(new RequestBall(), 
                                new Follow(), 
                                new RequestBay(), 
                                new Align(), 
                                new Follow());

  /*****
   * Drivetrain
   */

  public final int drivetrainLeftMotorOnePort = 1;
  public final int drivetrainLeftMotorTwoPort = 2;
  public final int drivetrainLeftMotorThreePort = 3;
  
  public final int drivetrainRightMotorOnePort = 6; //4;
  public final int drivetrainRightMotorTwoPort = 5;
  public final int drivetrainRightMotorThreePort = 4; //6;

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

  public final double climberSpeed = 0.8;

  /*****
   * Funnel
   */

  public final int funnelMotorOnePort= 9;

  public final double funnelSpeed = 0.5;

  /*****
   * Intake
   */
 
 //set climber port to 0 bc runs should be 8

  public final int intakeMotorOnePort = 8;

  public final int intakeSolOnPort = 6;
  public final int intakeSolOffPort = 7;

  public final double intakeSpeed = 0.8;

  /*****
   * Storage
   */

  public final int storageMotorOnePort = 13; // no motorized storage, set to obscure value for init purposes
  public final int storageMotorTwoPort = 14;
  public final double storageSpeedOne = 1.0; // no motorized storage
  public final double storageSpeedTwo = 1.0;

  /*****
   * Shooter
   */

  public final int shooterMotorOnePort = 10;
  public final int shooterMotorTwoPort = 11;

  public int shooterMaxRPM = 3000;
  public final double distanceToRpm = 0.21;
  /*****
   * Vision
   */

  public final int visionLightPortOne = 12;

  public final double visionLightIntensity = 1.0;
  
  /*****
   * Input
   */

  // public final int joystickOnePort = 0;
  // public final int gamepadOnePort = 1;
 
  // public final int climberToggleSolenoidButton = Gamepad.kGamepadButtonBack;
  // public final int climberRollWinchButton = Gamepad.kGamepadButtonA;
  // public final int climberUnrollButton = Gamepad.kGamepadButtonStart;

  // public final int drivetrainShiftButton = 1;
  // public final int drivetrainReverseDirectionButton = 3;
  // public final int drivetrainCoolFalconsButton = -3;

  // // public final int funnelMoveBallsForwardButton = Gamepad.kGamepadButtonX;
  // // public final int funnelMoveBallsReverseButton = -1; //not used

  // public final int intakeSpinRollerForwardButton = Gamepad.kGamepadButtonShoulderR;
  // public final int intakeSpinRollerReverseButton = -1; //not used
  // public final int intakeToggleSolenoidButton = Gamepad.kGamepadButtonX;

  // public final int shooterSpinForwardButton = Gamepad.kGamepadButtonShoulderL;
  // // public final int shooterStopButton = Gamepad.kGamepadTriggerLeft;
  // public final int shooterSpinReverseButton = -1; //not used

  // public final int storageMoveBallsForwardButton = Gamepad.kGamepadButtonY;
  // public final int storageMoveBallsReverseButton = -1; //not used

  // public final int visionAlignButton = 4;
  // public final int bayButton = 7;
  // public final int portButton = 11;
  // public final int ballButton = 9;
  // public final int connectButton = 12;
  // public final int cameraButton = 10;

  // public final int vomitButton = Gamepad.kGamepadButtonB; //used in place of all "Reverse" buttons

  // public final int startRecordingButton = 5;
  // public final int endRecordingButton = 6;



  
}
