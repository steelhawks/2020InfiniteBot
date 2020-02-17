
package frc.robot;
import frc.util.Gamepad;

public class ButtonMap{


  public final int joystickOnePort = 0;
  public final int gamepadOnePort = 1;
 
  public final int climberToggleSolenoidButton = Gamepad.kGamepadButtonBack;
  public final int climberRollWinchButton = Gamepad.kGamepadButtonA;
  public final int climberUnrollButton = Gamepad.kGamepadButtonStart;

  public final int drivetrainShiftButton = 1;
  public final int drivetrainReverseDirectionButton = 3;
  public final int drivetrainCoolFalconsButton = -3;

  // public final int funnelMoveBallsForwardButton = Gamepad.kGamepadButtonX;
  // public final int funnelMoveBallsReverseButton = -1; //not used

  public final int intakeSpinRollerForwardButton = Gamepad.kGamepadButtonShoulderR;
  public final int intakeSpinRollerReverseButton = -1; //not used
  public final int intakeToggleSolenoidButton = Gamepad.kGamepadButtonX;

  public final int shooterSpinForwardButton = Gamepad.kGamepadButtonShoulderL;
  // public final int shooterStopButton = Gamepad.kGamepadTriggerLeft;
  public final int shooterSpinReverseButton = -1; //not used

  public final int storageMoveBallsForwardButton = Gamepad.kGamepadButtonY;
  public final int storageMoveBallsReverseButton = -1; //not used

  public final int visionAlignButton = 4;
  public final int bayButton = 7;
  public final int portButton = 11;
  public final int ballButton = 9;
  public final int connectButton = 12;
  public final int cameraButton = 10;

  public final int vomitButton = Gamepad.kGamepadButtonB; //used in place of all "Reverse" buttons

  public final int startRecordingButton = 5;
  public final int endRecordingButton = 6;

}