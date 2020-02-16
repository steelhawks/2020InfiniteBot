
package frc.util.websocket;

import java.net.URI;
import java.util.concurrent.TimeUnit;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.*;

public class DashboardWS {
    private WebSocketClient dashboardWS;
    String controls = "";

    public DashboardWS(){
            try {
                dashboardWS = new WebSocketClient(new URI("ws://10.26.1.74:8080/dashboard/ws"), new Draft_6455()){
                    @Override
                    public void onOpen(ServerHandshake handshakedata) {
                        System.out.println("Socket opened");
                    }
                
                    @Override
                    public void onMessage(String message) {
                        controls = message;
                    }
                
                    @Override
                    public void onError(Exception ex) {
                        System.out.println("ERROR" + ex);
                        dashboardWS.close();
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

    public void send(String cameraMode){
        try{
            String message = "{\"controls\":{\"camera_mode\":\"" + cameraMode + "\"}}";
            System.out.println(message);
            dashboardWS.send(message);
        }
        catch(Exception e){
            System.out.println("couldn't send message");
        }
        
    }

    public String getCameraMode(){
        try{
            JSONObject websocketData = new JSONObject(controls);
            JSONObject visionControls = (JSONObject)(websocketData.get("controls"));
            return visionControls.getString("camera_mode");

        }
        catch(Exception e){
            //System.out.println("couldn't find controls");
            return "Object not found";
        }
    }

    public void connect(){
        try{
            dashboardWS.connect();
            System.out.println("connecting to socket");
        }
        catch(Exception e){
            System.out.println("Socket not open");
        }
    }
    
    public DashboardWS reconnect(){
        try{
            if(dashboardWS.isClosed()){
                DashboardWS newDashboardWS = new DashboardWS();
                newDashboardWS.connect();
                return newDashboardWS;
            }
            return this;
        }
        catch(Exception e){
            return this;
        }
    }

    public void baseConfig(){
      try
    {
      TimeUnit.SECONDS.sleep(1);
      dashboardWS.send("RAW");
    }
    catch (Exception e)
    {
      System.out.println("Couldn't sleep");
      dashboardWS.send("RAW");
    }
    }
}