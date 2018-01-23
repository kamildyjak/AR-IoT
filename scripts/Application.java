package project;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.Timer;

public class Application {

    public static void main(String[] args) throws UnirestException {

        NasConnection connection = NasConnection.getInstance();
        Nas nas = new Nas();

        connection.setNas(nas);
        connection.login();

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(connection, 0, 3 * 1000);

        connection.logout();
    }


}
