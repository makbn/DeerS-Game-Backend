package server;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import net.liftweb.http.js.JE;

import java.net.URISyntaxException;

/**
 * Created by white on 2016-11-11.
 */
public class DeerTest {

    private static DeerTest test;
    private static Socket socket;
    private static String hostName;
    private static int port;

    private DeerTest(String hostName, int port){
        this.hostName=hostName;
        this.port=port;
        try {
            socket = IO.socket(correctHostName(hostName)+":"+port);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static DeerTest getInstance(String hostName,int port){
        if(test==null)
            test=new DeerTest(hostName,port);
        return test;
    }

    public void connectToServer(){
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            public void call(Object... args) {
                System.out.print("Client connected\n > ");
                socket.emit("client");
            }

        });
        socket.on(Socket.EVENT_CONNECTING, new Emitter.Listener() {

            public void call(Object... args) {
                System.out.print("Client connecting\n > ");
            }

        });
        socket.on(Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {

            public void call(Object... args) {
                System.out.print("Client connect error\n > ");
            }

        });

        socket.connect();
    }


    private String correctHostName(String hostName){
        if(hostName.startsWith("http")){
            return hostName;
        }else {
            return "http://"+hostName;
        }
    }
}
