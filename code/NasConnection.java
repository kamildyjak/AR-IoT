package project;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.TimerTask;

public class NasConnection extends TimerTask {
    private static NasConnection instance = null;
    private boolean connected;
    private String ip;
    private Nas nas;

    private NasConnection() {
        connected = false;
        ip = "....";
    }

    public static NasConnection getInstance() {
        if (instance == null) {
            instance = new NasConnection();
        }
        return instance;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setNas(Nas nas) {
        this.nas = nas;
    }

    @Override
    public void run() {
        nas.run();
    }


    public HttpResponse<String> login() {
        HttpResponse<String> response = null;
        try {
            response = Unirest.post(ip + "cgi-bin/login_mgr.cgi")
                    .header("content-type", "multipart/form-data; " +
                            "boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data;" +
                            "name=\"cmd\"\r\n\r\nwd_login\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data; " +
                            "name=\"username\"\r\n\r\nadmin\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data; " +
                            "name=\"pwd\"\r\n\r\nRHlqdTcxMWthbWls\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data; " +
                            "name=\"port\"\r\n\r\n\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                    .asString();
            if (response.getStatus() == 200) {
                connected = true;
            } else {
                connected = false;
            }
        } catch (UnirestException e) {
            connected = false;
        }
        return response;
    }

    public HttpResponse<String> logout(){
        HttpResponse<String> response = null;
        try {
            response = Unirest.post(ip + "cgi-bin/login_mgr.cgi")
                    .header("content-type", "multipart/form-data; " +
                            "boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
                    .body("------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data; " +
                            "name=\"cmd\"\r\n\r\nwd_logout\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\n" +
                            "Content-Disposition: form-data; " +
                            "name=\"name\"\r\n\r\nadmin\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--")
                    .asString();
        } catch (UnirestException e) {
        }
        return response;
    }


    protected HttpResponse<String> getInfo() {
        HttpResponse<String> response = null;
        try {
            response = Unirest.get(ip + "xml/sysinfo.xml")
                    .asString();
            if (response.getStatus() == 200) {
                connected = true;
            } else {
                connected = false;
            }
        } catch (UnirestException e) {
            connected = false;
        }

        return response;
    }

}
