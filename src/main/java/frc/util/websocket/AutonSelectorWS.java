/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.util.websocket;

import frc.robot.Robot;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class AutonSelectorWS extends WebSocketServer{
     
    public AutonSelectorWS(){
      super(new InetSocketAddress("10.26.0.1", 5805));
    }
    
    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
      System.out.println("new connection to " + conn.getRemoteSocketAddress());
    }
  
    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
      System.out.println("closed " + conn.getRemoteSocketAddress() + " with exit code " + code + " additional info: " + reason);
    }
  
    @Override
    public void onMessage(WebSocket conn, String message) {
      System.out.println("received message from "	+ conn.getRemoteSocketAddress() + ": " + message);
      Robot.ROBOT_MAP.paths.add(message);
      Robot.FOLLOWER.importPath(Robot.ROBOT_MAP.paths);
    }
  
    @Override
    public void onMessage( WebSocket conn, ByteBuffer message ) {
      System.out.println("received ByteBuffer from "	+ conn.getRemoteSocketAddress());
    }
  
    @Override
    public void onError(WebSocket conn, Exception ex) {
      System.err.println("an error occurred on connection " + conn.getRemoteSocketAddress()  + ":" + ex);
    }
    
    @Override
    public void onStart() {
      System.out.println("server started successfully");
    }

    public void start(){
        try{
            Robot.AUTON_SELECTORWS.run();
        }
        catch(Exception e){
            System.out.println("cannot start websocket server");
        }
    }
    
}