package project;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VolThing extends ThingworxTemplate {

    public void putRequest(String parameter, JsonBody body) {
        String name = "Volume" + id;
        sendPutRequest(name, parameter, body);
    }

    public void pushInfo() {
        Set set = information.entrySet();
        Iterator iterator = set.iterator();
        System.out.println("Put volume " + id);
        JsonBody body = new JsonBody();


        while (iterator.hasNext()) {
            Map.Entry info = (Map.Entry) iterator.next();
            body.addParameter((String) info.getKey(), (String) info.getValue());
            System.out.println("\t" + info.getKey() + " : " + info.getValue());
            putRequest((String) info.getKey(), body);
        }

    }

    public HttpResponse<String> postRequest(String service, JsonBody body) throws UnirestException {
        return null;
    }
}
