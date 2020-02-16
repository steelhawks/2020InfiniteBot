package frc.util.pathcorder;
import java.util.ArrayList;

public class AutonPath {
    public ArrayList<Double> joystickYValues;
    public ArrayList<Double> joystickRotationValues;
    public ArrayList<Double> encoderRightValues = new ArrayList<Double>(0);
    public ArrayList<Double> encoderLeftValues = new ArrayList<Double>(0);

    public AutonPath(){
        joystickYValues = new ArrayList<Double>(0);
        joystickRotationValues = new ArrayList<Double>(0);
        encoderRightValues = new ArrayList<Double>(0);
        encoderLeftValues = new ArrayList<Double>(0);
    }
}