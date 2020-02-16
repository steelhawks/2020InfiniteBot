
package frc.util.websocket;

import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

public class TrackingWS {
    private String targetData;
    private WebSocketClient trackingWS;

    public TrackingWS(){
            try {
                trackingWS = new WebSocketClient(new URI("ws://10.26.1.74:8080/tracking/ws"), new Draft_6455()){
                    @Override
                    public void onOpen(ServerHandshake handshakedata) {
                        System.out.println("Socket opened");
                    }
                
                    @Override
                    public void onMessage(String message) {
                        // System.out.println(message);
                        targetData = message;
                    }
                
                    @Override
                    public void onError(Exception ex) {
                        System.out.println("ERROR" + ex);
                        trackingWS.close();
                    }
                
                    @Override
                    public void onClose(int code, String reason, boolean remote) {
                        System.out.println("socket closed");
                    }
                };
            } 
            catch (Exception e) {
                //TODO: handle exception
                System.out.println("couldn't create a client socket");
            }
    
            
    }

    public String getTargetData() {
        return targetData;
    }

    public void connect(){
        try{
            trackingWS.connect();
        }
        catch(Exception e){
            System.out.println("Socket not open");
        }
    }
    
    public TrackingWS reconnect(){
        try{
            if(trackingWS.isClosed()){
                TrackingWS newtrackingWS = new TrackingWS();
                newtrackingWS.connect();
                return newtrackingWS;
            }
            return this;
        }
        catch(Exception e){
            return this;
        }
    }
    
}