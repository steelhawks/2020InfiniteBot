package frc.util.pathcorder;

public class JoystickRecorder
{
    public final double joystickY;
    public final double joystickTwist;
    public final boolean shifted;
    public final int count; 

    public JoystickRecorder(double joystickY, double joystickTwist, boolean shifted, int count)
    {
        this.joystickY = joystickY;
        this.joystickTwist = joystickTwist;
        this.shifted = shifted;
        this.count = count; 
    }

    public String toString()
    {
        return "Y: " + this.joystickY + "\nTwist: " + this.joystickTwist + "\nShifted: " + this.shifted;
    }
    
}