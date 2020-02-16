package frc.util.pathcorder;

import com.revrobotics.CANEncoder;



public class Encorder
{
    
   public double rightEncoder;
   public double leftEncoder;

   public Encorder(CANEncoder right, CANEncoder left)
   {
       
       rightEncoder = right.getPosition();
       leftEncoder = left.getPosition();
   }
  
   public boolean isTurning()
   {
       boolean turning;

       if((rightEncoder > leftEncoder) || (leftEncoder > rightEncoder))
       {
         turning = true;
       }
       else
       {
           turning = false;
       }
       return turning;
   }
   
   public String toString()
   {
       return ("Right Encoder: " + this.rightEncoder +"\nLeft Encoder: " + this.leftEncoder);
       
   }




   
}
