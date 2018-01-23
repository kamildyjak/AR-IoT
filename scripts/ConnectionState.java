package project;

import com.mashape.unirest.http.HttpResponse;

public abstract class ConnectionState {
    protected static Nas nas;
    protected static Parser parser;

    protected abstract void processDeviceInfo(HttpResponse<String> systemInfo);

    protected void setConnections(Nas nas, Parser parser) {
        this.nas = nas;
        this.parser = parser;
    }

}
