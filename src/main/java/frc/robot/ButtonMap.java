/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import frc.util.Gamepad;

public class ButtonMap {
  public final int joystickOnePort = 0;
  public final int gamepadOnePort = 1;

  public final int climberToggleSolenoidButton = Gamepad.kGamepadButtonBack;
  public final int climberToggleSafetySolenoidButton = 10;
  public final int climberRollWinchButton = Gamepad.kGamepadButtonA;
  public final int climberUnrollButton = Gamepad.kGamepadButtonStart;

  public final int intakeSpinRollerForwardButton = Gamepad.kGamepadButtonShoulderR;
  public final int intakeToggleSolenoidButton = Gamepad.kGamepadButtonX;
  public final int intakeSpinRollerReverseButton = Gamepad.kGamepadButtonLeftStick;

  public final int shooterSpinForwardButton = Gamepad.kGamepadButtonShoulderL;

  public final int storageMoveBallsForwardButton = Gamepad.kGamepadButtonY;
  public final int StorageMoveBallsReverseButton = Gamepad.kGamepadButtonB;

  public final int drivetrainShiftButton = 1;
  public final int drivetrainReverseDirectionButton = 3;
  public final int drivetrainCoolFalconsButton = -3;

  public final int visionAlignButton = 4;
  public final int visionRequestBayButton = 7;
  public final int visionRequestPortButton = 11;
  public final int visionRequestBallButton = 9;
  public final int visionRequestConnectButton = 12;
  public final int visionToggleAlignButton = 8;

  public final int startRecordingButton = 5;
  public final int endRecordingButton = 6;

  public final int turretToggleTestingButton = 8;

}