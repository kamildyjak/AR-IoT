package project;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.HashMap;

public abstract class ThingworxTemplate implements EdgeAgent {
    static String server = "https://usw9ua3k.studio-trial.thingworx.io/Thingworx/";
    static String appKey = "....";

    protected int id;
    protected HashMap<String, String> information;

    public ThingworxTemplate() {
        id = 0;
        information = new HashMap<>();
    }

    protected abstract void pushInfo();

    protected void sendPutRequest(String name, String parameter, JsonBody body) {
        try {
            Unirest.put(server + "Things/" + name + "/Properties/" + parameter)
                    .header("appkey", appKey)
                    .header("content-type", "application/json")
                    .body(body.getBody())
                    .asString();
        } catch (UnirestException e) {
            //e.printStackTrace();
        }
    }


}
