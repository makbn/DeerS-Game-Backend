package server;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import util.DeerDashboard;
import util.UserManager;

/**
 * Created by white on 2016-11-06.
 */
public class Deer {
    private static Configuration configuration;
    private static SocketIOServer socketIOServer;
    private final static String HOST_NAME="localhost";
    private final static int PORT=3320;

    /**
     * starting point of application
     *
     * @param args not important
     */
    public static void main(String[] args){
        configuration=getConfig(HOST_NAME,PORT);
        socketIOServer=new SocketIOServer(configuration);
        UserManager.getInstance(socketIOServer).run();
        socketIOServer.start();
        DeerDashboard.startDashboard();
        socketIOServer.stop();
    }

    /**
     * get configuration for connecting socket
     * @param hostName If not set then bind address will be 0.0.0.0 or ::0
     * @param port The port the socket.io server will listen to
     * @return
     */
    public static Configuration getConfig(String hostName,int port){
        Configuration config = new Configuration();
        if(hostName!=null)
            config.setHostname(hostName);
        config.setPort(port);
        return config;
    }

    public static String getHostName(){
        return HOST_NAME;
    }
    public static int getPort(){
        return PORT;
    }
}
