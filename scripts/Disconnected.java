package project;

import com.mashape.unirest.http.HttpResponse;

public class Disconnected extends ConnectionState {

    private static Disconnected instance = null;

    private Disconnected() {
    }

    public static Disconnected getInstance() {
        if (instance == null) {
            instance = new Disconnected();
        }
        return instance;
    }

    public void processDeviceInfo(HttpResponse<String> systemInfo) {
        System.out.println("Disconnected");
        JsonBody body = new JsonBody();
        body.addParameter("Connected", "false");
        nas.putRequest( "Connected", body);
        nas.reconnect();
    }
}
