package project;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface EdgeAgent {
    void putRequest(String parameter, JsonBody body);
    HttpResponse<String> postRequest(String service, JsonBody body) throws UnirestException;

}
