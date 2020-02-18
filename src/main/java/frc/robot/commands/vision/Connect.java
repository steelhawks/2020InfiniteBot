package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Robot;

import java.util.HashSet;
import java.util.Set;

public class Connect implements Command
{
    public Connect(){}
    @Override
    public Set<Subsystem> getRequirements() 
    {
        Set<Subsystem> list = new HashSet<Subsystem>();
        list.add(Robot.VISION);
        return list;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute()
    {
        try{
            Robot.TRACKINGWS.reconnect();
            Robot.DASHBOARDWS.reconnect();
        }
        catch(Exception e){
            System.out.println("idk man i cant connect");

        }
        
    }

    @Override
    public boolean isFinished()
    {
        return true;
    }

    @Override
    public void end(boolean interrupted) {}

}