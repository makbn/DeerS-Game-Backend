package util;

import Model.Client;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import io.socket.client.Socket;
import scala.collection.mutable.LinkedHashSet;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by white on 2016-11-06.
 */
public class UserManager {
    private static SocketIOServer socketIOServer;
    private static UserManager mUserManager;
    private static LinkedHashMap<UUID, Client> clients;

    private UserManager(SocketIOServer socketIOServer){
        this.socketIOServer=socketIOServer;
        clients=new LinkedHashMap<UUID, Client>();
    }

    /**
     * get an instance of UserManagement class
     * @param socketIOServer
     * @return
     */
    public static UserManager getInstance( SocketIOServer socketIOServer){
        if(mUserManager==null){
            mUserManager=new UserManager(socketIOServer);
        }
        return mUserManager;
    }

    /**
     * start listening to socket for connection new client and manage them!
     */
    public void run(){
        socketIOServer.addEventListener("client", Object.class, new DataListener<Object>() {
            public void onData(SocketIOClient socketIOClient, Object o, AckRequest ackRequest) throws Exception {
                  clients.put(socketIOClient.getSessionId(),new Client(socketIOClient));
            }
        });
    }

    public static ArrayList<UUID> getClientsId(){
        ArrayList<UUID>uuids=new ArrayList<UUID>();
        for(Map.Entry<UUID,Client> s:clients.entrySet()){
            uuids.add(s.getKey());
        }
        return uuids;
    }
}
