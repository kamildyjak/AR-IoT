package project;

import com.mashape.unirest.http.HttpResponse;


import java.util.*;

public class Nas extends NasThing {

    private HashMap<String, String> deviceInfo;
    private ArrayList<Disk> diskManager;
    private ArrayList<Raid> raidManager;
    private ArrayList<Vol> volManager;
    private ConnectionState state;
    private NasConnection connection;
    private Parser parser;

    public Nas() {
        connection = NasConnection.getInstance();
        parser = new XmlParser();
        diskManager = new ArrayList<>();
        raidManager = new ArrayList<>();
        volManager = new ArrayList<>();
        deviceInfo = new HashMap<>();
        state = Disconnected.getInstance();
        state.setConnections(this, parser);
    }


    public void setState(ConnectionState state) {
        this.state = state;
    }

    public void setParser(Parser parser){
        this.parser = parser;
    }


    public void updateDisks(ArrayList<Disk> disks) {
        diskManager = disks;
    }

    public void updateRaids(ArrayList<Raid> raids) {
        raidManager = raids;
    }

    public void updateVolumes(ArrayList<Vol> volumes) {
        volManager = volumes;
    }

    public void updateGeneralInfo(HashMap<String, String> info) {
        deviceInfo = info;
    }

    public void reconnect() {
        connection.login();
    }


    public void run() {
        HttpResponse<String> systemInfo = connection.getInfo();
        if (connection.isConnected()) {
            setState(Connected.getInstance());
        } else {
            setState(Disconnected.getInstance());
        }
        state.processDeviceInfo(systemInfo);

    }


    public void pushInfo() {
        for (int i = 0; i < diskManager.size(); i++) {
            diskManager.get(i).pushInfo();
        }

        for (int i = 0; i < raidManager.size(); i++) {
            raidManager.get(i).pushInfo();
        }

        for (int i = 0; i < volManager.size(); i++) {
            volManager.get(i).pushInfo();
        }
    }


    public void display() {
        Set set = deviceInfo.entrySet();
        Iterator iterator = set.iterator();
        System.out.println("WD system information");
        while (iterator.hasNext()) {
            Map.Entry info = (Map.Entry) iterator.next();
            System.out.println("\t" + info.getKey() + " : " + info.getValue());
        }
        System.out.println("\nDisks");
        for (int i = 0; i < diskManager.size(); i++) {
            diskManager.get(i).display();
        }
        System.out.println("\nRAIDs");
        for (int i = 0; i < raidManager.size(); i++) {
            raidManager.get(i).display();
        }
        System.out.println("\nVolumes");
        for (int i = 0; i < volManager.size(); i++) {
            volManager.get(i).display();
        }
        System.out.println("\n");
    }
}
