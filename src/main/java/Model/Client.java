package Model;

import com.corundumstudio.socketio.SocketIOClient;
import com.onyx.persistence.ManagedEntity;
import net.liftweb.http.js.JE;
import org.joda.time.DateTime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by white on 2016-11-07.
 */
public class Client extends ManagedEntity {
    private String id;
    private SocketIOClient client;
    private DateTime connectDate;
    private Location location;

    public Client(SocketIOClient client){
        this.client=client;
        this.id=client.getSessionId().toString();
        connectDate=new DateTime();
    }

    public void getLocation() throws IOException {
        Thread t=new Thread(new Runnable() {
            public void run() {
                String IP= client.getRemoteAddress().toString();
                URL link = null;
                try {
                    link = new URL("http://www.geobytes.com/IpLocator.htm?GetLocation&template=php3.txt&IpAddress="+IP);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                BufferedReader in = null;
                try {
                    in = new BufferedReader(new InputStreamReader(link.openStream()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String inputLine;

                try {
                    while ((inputLine = in.readLine()) != null){
                        location=new Location();
                        location.parsLoacation(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

}
