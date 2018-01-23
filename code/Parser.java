package project;

import java.util.ArrayList;
import java.util.HashMap;

public interface Parser {
    void setDocument(String input);

    ArrayList<Disk> getDisksInfo();

    ArrayList<Raid> getRaidsInfo();

    ArrayList<Vol> getVolumesInfo();

    HashMap<String, String> getDeviceInfo();
}
