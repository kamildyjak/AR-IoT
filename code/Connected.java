package project;

import com.mashape.unirest.http.HttpResponse;

import java.util.ArrayList;
import java.util.HashMap;

public class Connected extends ConnectionState {

    private static Connected instance = null;

    private Connected() {
    }

    public static Connected getInstance() {
        if (instance == null) {
            instance = new Connected();
        }
        return instance;
    }

    public void processDeviceInfo(HttpResponse<String> systemInfo) {
        parser.setDocument(systemInfo.getBody());

        ArrayList<Disk> disks = parser.getDisksInfo();
        nas.updateDisks(disks);

        ArrayList<Raid> raids = parser.getRaidsInfo();
        nas.updateRaids(raids);

        ArrayList<Vol> volumes = parser.getVolumesInfo();
        nas.updateVolumes(volumes);

        HashMap<String, String> general = parser.getDeviceInfo();
        nas.updateGeneralInfo(general);

        nas.pushInfo();

    }
}
